package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

@Feature("Navigation")
public class NavigatePageTest extends BaseTest {

    @Test
    @Description("Verify navigation to task page and main page is visible")
    public void navigateToTaskPage() {
        mainSteps
                .navigateToTaskPage()
                .validateMainPage();
    }
}