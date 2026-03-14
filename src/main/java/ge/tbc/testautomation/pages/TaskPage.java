package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TaskPage {
    private final Page page;

    public Locator taskName,
            dropDownTrigger, dropDownOptions, secondDropDownTrigger,
            descriptionInput, startDate,
            endDate, startCalendarGrid, endCalendarGrid,
            rewardInput, nextBtn, questionAmountInputLocator,
            timeAmountInputLocator,completeButton,nextQuestionBtn,saveChanges;

    public TaskPage(Page page) {
        this.page = page;
        this.taskName = page.locator("input.lib-input-field").first();
        this.dropDownTrigger = page.locator("button.lib-dropdown-trigger").first();
        this.nextQuestionBtn = page.locator(".lib-btn.lib-btn--md.lib-btn--primary").last();
        this.secondDropDownTrigger = page.locator("button.lib-dropdown-trigger").last();
        this.dropDownOptions = page.locator("ul.lib-dropdown-menu li");
        this.descriptionInput = page.locator(".lib-input-field.lib-input-field--textarea");
        this.startDate = page.locator("div.datepicker-trigger").first();
        this.endDate = page.locator("div.datepicker-trigger").last();
        this.startCalendarGrid = page.locator(".calendar-grid").first();
        this.endCalendarGrid = page.locator(".calendar-grid").first();
        this.rewardInput = page.locator("input.lib-input-field[type='number']");
        this.nextBtn = page.getByText("Next").last();
        this.questionAmountInputLocator = page.locator("input.lib-input-field").first();
        this.timeAmountInputLocator = page.locator("input.lib-input-field").last();
        this.completeButton = page.getByText(" Complete ");
        this.saveChanges = page.getByText("Save Changes");
    }
}