package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Delete User")
public class DeleteUserScenario extends BaseTest {

    @BeforeClass
    public void navigateToPage() {
        mainSteps.navigateToTaskPage();
    }

    @Test
    @Description("Click the delete button on the user")
    public void clickDeleteButton() {
        parentalControlsSteps.clickDelete();
    }

    @Test(dependsOnMethods = "clickDeleteButton")
    @Description("Confirm the deletion")
    public void confirmDeletion() {
        parentalControlsSteps.confirmDelete();
    }
}