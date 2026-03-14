package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Edit Task")
public class EditTaskScenario extends BaseTest {

    @BeforeClass
    public void navigateToPage() {
        mainSteps.navigateToTaskPage();
    }

    @Test
    @Description("Navigate to the task to edit")
    public void navigateToTask() {
        parentalControlsSteps
                .clickForward()
                .clickEdit();
    }

    @Test(dependsOnMethods = "navigateToTask")
    @Description("Edit the task name and save")
    public void editAndSaveTask() {
        taskSteps
                .giveTaskName("bzz")
                .saveChangesBtn();
    }
}