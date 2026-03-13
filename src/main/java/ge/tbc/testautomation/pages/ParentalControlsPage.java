package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ParentalControlsPage {
    private final Page page;

    public Locator createTasksButton,taskName,
            dropDownTrigger, dropDownOptions,secondDropDownTrigger
            ,descriptionInput,startDate
            ,endDate,calendarGrid,
            rewardInput,nextBtn,questionAmountInputLocator,timeAmountInputLocator
            ,nameValidation,forwardBtn,backBtn;

    public ParentalControlsPage(Page page) {
        this.page = page;
        this.nameValidation = page.locator("h2.banner__name");
        this.forwardBtn = page.locator(".lib-btn.lib-btn--ghost.lib-btn--icon-only.lib-btn--md").first();
        this.backBtn = page.locator(".lib-btn.lib-btn--ghost.lib-btn--icon-only.lib-btn--md").last();
        this.createTasksButton = page.getByText("Create Task");
        this.taskName = page.locator("input.lib-input-field").first();
        this.dropDownTrigger = page.locator("button.lib-dropdown-trigger").first();
        this.secondDropDownTrigger = page.locator("button.lib-dropdown-trigger").last();
        this.dropDownOptions = page.locator("ul.lib-dropdown-menu li");
        this.descriptionInput = page.locator(".lib-input-field.lib-input-field--textarea");
        this.startDate = page.locator("div.datepicker-trigger").first();
        this.endDate = page.locator("div.datepicker-trigger").last();
        this.calendarGrid = page.locator(".calendar-grid").last();
        this.rewardInput = page.locator("input.lib-input-field[type='number']");
        this.nextBtn = page.getByText("Next").last();
        this.questionAmountInputLocator = page.locator("input.lib-input-field").first();
        this.timeAmountInputLocator = page.locator("input.lib-input-field").last();
    }
}
