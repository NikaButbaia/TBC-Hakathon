package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.TaskPage;
import io.qameta.allure.Step;

public class TaskSteps {

    private final TaskPage taskPage;
    private final Page page;

    public TaskSteps(Page page) {
        this.page = page;
        this.taskPage = new TaskPage(page);
    }

    @Step("Give task name: {name}")
    public TaskSteps giveTaskName(String name) {
        taskPage.taskName.fill(name);
        return this;
    }

    @Step("Select task at index: {index}")
    public TaskSteps selectTask(int index) {
        taskPage.dropDownTrigger.click();
        taskPage.dropDownOptions.nth(index).click();
        return this;
    }

    @Step("Type description: {description}")
    public TaskSteps typeDescription(String description) {
        taskPage.descriptionInput.fill(description);
        return this;
    }

    @Step("Select start day: {day}")
    public TaskSteps selectStartDay(int day) {
        taskPage.startDate.click();
        taskPage.startCalendarGrid
                .getByText(String.valueOf(day), new Locator.GetByTextOptions().setExact(true))
                .click();
        return this;
    }

    @Step("Select end day: {day}")
    public TaskSteps selectEndDay(int day) {
        taskPage.endDate.click();
        taskPage.endCalendarGrid
                .getByText(String.valueOf(day), new Locator.GetByTextOptions().setExact(true))
                .click();
        return this;
    }

    @Step("Add task")
    public TaskSteps addTask() {
        taskPage.completeButton.click();
        return this;
    }

    @Step("Input rewards: {amount}")
    public TaskSteps inputRewards(String amount) {
        taskPage.rewardInput.fill(amount);
        return this;
    }

    @Step("Select second dropdown at index: {index}")
    public TaskSteps selectSecondDropdown(int index) {
        page.keyboard().press("Escape");
        taskPage.secondDropDownTrigger.click();
        taskPage.dropDownOptions.nth(index).click();
        return this;
    }

    @Step("Click next button")
    public TaskSteps clickNextBtn() {
        taskPage.nextBtn.click();
        return this;
    }

    @Step("Click next question button {times} times")
    public TaskSteps clickNextQuestionBtn(int times) {
        for (int i = 0; i < times; i++) {
            taskPage.nextQuestionBtn.click();
        }
        return this;
    }

    @Step("Save changes")
    public TaskSteps saveChangesBtn() {
        taskPage.saveChanges.click();
        return this;
    }

    @Step("Input question amount: {questionAmount} and time amount: {timeAmount}")
    public TaskSteps inputQuestionTimeAmount(String questionAmount, String timeAmount) {
        taskPage.questionAmountInputLocator.fill(questionAmount);
        taskPage.timeAmountInputLocator.fill(timeAmount);
        return this;
    }
}