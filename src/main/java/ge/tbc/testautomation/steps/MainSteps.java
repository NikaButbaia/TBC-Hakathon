package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.MainPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MainSteps {

    private final MainPage mainPage;

    public MainSteps(Page page) {
        this.mainPage = new MainPage(page);
    }

    @Step("Navigate to Task Page")
    public MainSteps navigateToTaskPage() {
        mainPage.navigateToTasksPage.click();
        return this;
    }

    @Step("Validate Main Page is visible")
    public MainSteps validateMainPage() {
        assertThat(mainPage.mainPageValidation).isVisible();
        return this;
    }
}