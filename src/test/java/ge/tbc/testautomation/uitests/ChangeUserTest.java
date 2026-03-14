package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("User Navigation")
public class ChangeUserTest extends BaseTest {

    @BeforeClass
    public void navigateToPage() {
        mainSteps.navigateToTaskPage();
    }

    @Test
    @Description("Verify that the user name changes after clicking forward")
    public void testNameChangesOnForward() {
        parentalControlsSteps
                .captureNameBefore()
                .clickForward()
                .assertNameChanged();
    }
}