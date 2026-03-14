package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ParentalControlsPage {
    private final Page page;

    public Locator
            createTasksButton,nameValidation
            ,forwardBtn,backBtn,deleteBtn
            ,confirmDelete,editBtn;

    public ParentalControlsPage(Page page) {
        this.page = page;
        this.createTasksButton = page.locator("button.lib-btn.lib-btn--primary:has(span:text-is('Create Task'))");
        this.nameValidation = page.locator("h2.banner__name");
        this.forwardBtn = page.locator("button.lib-btn i.pi-chevron-right");
        this.backBtn = page.locator("button.lib-btn i.pi-chevron-left");
        this.deleteBtn = page.locator("button.lib-btn.lib-btn--ghost.lib-btn--icon-only.lib-btn--sm:has(i.pi-trash)");
        this.confirmDelete = page.locator("button.lib-btn:has-text('Delete')");
        this.editBtn = page.locator("button.lib-btn.lib-btn--ghost.lib-btn--icon-only.lib-btn--sm:has(i.pi-info-circle)").last();
    }
}
