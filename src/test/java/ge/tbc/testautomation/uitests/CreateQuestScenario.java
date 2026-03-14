package ge.tbc.testautomation.uitests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ge.tbc.testautomation.data.Constants.*;
import static ge.tbc.testautomation.data.TestDataGenerator.*;

@Feature("Create Quest")
public class CreateQuestScenario extends BaseTest {

    int questionAmount = randomQuestionAmount();

    @BeforeClass
    public void navigateToPage() {
        page.navigate(PARENTAL_CONTROLS_URL);
        parentalControlsSteps.openQuest();
    }

    @Test
    @Description("Fill in task details")
    public void fillTaskDetails() {
        taskSteps
                .giveTaskName(randomWord())
                .selectTask(TASK_TYPE_INDEX)
                .typeDescription(randomSentence())
                .selectStartDay(START_DAY)
                .selectEndDay(END_DAY)
                .inputRewards(randomReward());
    }

    @Test(dependsOnMethods = "fillTaskDetails")
    @Description("Configure question and time settings")
    public void configureQuestionSettings() {
        taskSteps
                .selectSecondDropdown(SECOND_DROPDOWN_INDEX)
                .clickNextBtn()
                .inputQuestionTimeAmount(
                        String.valueOf(questionAmount),
                        randomTimeAmount());
    }

    @Test(dependsOnMethods = "configureQuestionSettings")
    @Description("Complete quest creation by answering questions")
    public void completeQuestCreation() {
        taskSteps
                .clickNextBtn()
                .clickNextQuestionBtn(questionAmount);
    }
}