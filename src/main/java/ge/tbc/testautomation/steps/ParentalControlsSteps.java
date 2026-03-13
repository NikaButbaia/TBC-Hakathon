package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ParentalControlsPage;
import org.testng.Assert;

public class ParentalControlsSteps{

    private final ParentalControlsPage parentalControlsPage;
    private final Page page;


    public ParentalControlsSteps(Page page) {
        this.page = page;
        this.parentalControlsPage = new ParentalControlsPage(page);
    }

    public ParentalControlsSteps openQuest(){
        parentalControlsPage.createTasksButton.dispatchEvent("click");
        page.waitForTimeout(500);
        return this;
    }
    public ParentalControlsSteps giveTaskName(String name) {
        parentalControlsPage.taskName.waitFor();
        parentalControlsPage.taskName.fill(name);
        return this;
    }
    public ParentalControlsSteps selectTask(int index) {
        parentalControlsPage.dropDownTrigger.click();
        parentalControlsPage.dropDownOptions.nth(index).click();
        return this;
    }
    public  ParentalControlsSteps typeDescription(String description){
        parentalControlsPage.descriptionInput.fill(description);
        return this;
    }
    public ParentalControlsSteps inputStartDate(){
        parentalControlsPage.startDate.click();
        return this;
    }
    public ParentalControlsSteps inputEndDate(){
        parentalControlsPage.endDate.click();
        return this;
    }
    public ParentalControlsSteps selectDay(int day) {
        parentalControlsPage.calendarGrid
                .getByText(String.valueOf(day), new Locator.GetByTextOptions().setExact(true))
                .click();
        return this;
    }
    public ParentalControlsSteps inputRewards(String number) {
        parentalControlsPage.rewardInput.fill(number);
        return this;
    }
    public ParentalControlsSteps selectSecondDropdown(int index) {
        parentalControlsPage.secondDropDownTrigger.click();
        parentalControlsPage.dropDownOptions.nth(index).click();
        return this;
    }
    public ParentalControlsSteps clickNextBtn(){
        parentalControlsPage.nextBtn.click();
        return this;
    }
    public ParentalControlsSteps inputQuestionTimeAmount(String x, String y) {
        parentalControlsPage.questionAmountInputLocator.fill(x);
        parentalControlsPage.timeAmountInputLocator.fill(y);
        return this;
    }
    public ParentalControlsSteps clickForward() {
        parentalControlsPage.forwardBtn.click();
        return this;
    }

    public ParentalControlsSteps clickBack() {
        parentalControlsPage.backBtn.click();
        return this;
    }

    public ParentalControlsSteps assertNameChanged() {
        String nameBefore = parentalControlsPage.nameValidation.textContent();
        parentalControlsPage.forwardBtn.click();
        Assert.assertNotEquals(nameBefore, parentalControlsPage.nameValidation.textContent(), "Name should have changed");
        return this;
    }
}
