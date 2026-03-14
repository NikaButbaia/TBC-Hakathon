package ge.tbc.testautomation.uitests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.*;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.*;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected MainSteps mainSteps;
    protected ParentalControlsSteps parentalControlsSteps;
    protected TaskSteps taskSteps;

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("chromium") String browserName) {
        playwright = Playwright.create();

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(true)
                .setArgs(java.util.List.of("--start-maximized"));

        browser = switch (browserName.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        page = context.newPage();
        page.navigate(TBC_URL);

        mainSteps = new MainSteps(page);
        parentalControlsSteps = new ParentalControlsSteps(page);
        taskSteps = new TaskSteps(page);
    }

    @AfterClass
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}