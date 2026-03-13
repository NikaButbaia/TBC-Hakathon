package ge.tbc.testautomation.uitests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.*;
import ge.tbc.testautomation.utils.Utils;
import org.testng.annotations.*;

import static ge.tbc.testautomation.data.Constants.*;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected Utils utils;
    protected MainSteps mainSteps;
    protected ParentalControlsSteps parentalControlsSteps;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(java.util.List.of("--start-maximized")));

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

        page = context.newPage();
        page.navigate(TBC_URL);

        utils = new Utils(page);
        mainSteps = new MainSteps(page);
        parentalControlsSteps = new ParentalControlsSteps(page);
    }

    @AfterClass
    public void tearDown() {
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}