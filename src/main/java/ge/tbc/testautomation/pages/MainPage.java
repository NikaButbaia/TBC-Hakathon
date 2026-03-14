package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {

    private final Page page;

    public Locator navigateToTasksPage, mainPageValidation;


    public MainPage(Page page) {
        this.page = page;
        this.navigateToTasksPage = page.locator("button.lib-btn.lib-btn--primary:has(i.pi-plus-circle)");
        this.mainPageValidation = page.locator("img.header-logo");
    }
}