package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ParentalControlsPage;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ParentalControlsSteps {

    private final ParentalControlsPage parentalControlsPage;
    private final Page page;
    private String nameBefore;

    public ParentalControlsSteps(Page page) {
        this.page = page;
        this.parentalControlsPage = new ParentalControlsPage(page);
    }

    @Step("Open quest")
    public ParentalControlsSteps openQuest() {
        parentalControlsPage.createTasksButton.click();
        return this;
    }

    @Step("Capture name before navigation")
    public ParentalControlsSteps captureNameBefore() {
        this.nameBefore = parentalControlsPage.nameValidation.textContent();
        return this;
    }

    @Step("Click forward button")
    public ParentalControlsSteps clickForward() {
        parentalControlsPage.forwardBtn.click();
        return this;
    }

    @Step("Assert name has changed after clicking forward")
    public ParentalControlsSteps assertNameChanged() {
        String nameAfter = parentalControlsPage.nameValidation.textContent();
        Assert.assertNotEquals(nameBefore, nameAfter, "Name should have changed after clicking forward");
        return this;
    }

    @Step("Click delete button")
    public ParentalControlsSteps clickDelete() {
        parentalControlsPage.deleteBtn.click();
        return this;
    }

    @Step("Confirm deletion")
    public ParentalControlsSteps confirmDelete() {
        parentalControlsPage.confirmDelete.click();
        return this;
    }

    @Step("Click edit button")
    public ParentalControlsSteps clickEdit() {
        parentalControlsPage.editBtn.click();
        return this;
    }
}