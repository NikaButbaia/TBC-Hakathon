package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.MainPage;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MainSteps {

    private final MainPage mainPage;

    public MainSteps(Page page) {
        this.mainPage = new MainPage(page);
    }

    public MainSteps navigateToTaskPage() {
        mainPage.navigateToTasksPage.click();
        return this;
    }
    public MainSteps validateMainPage(){
        assertThat(mainPage.mainPageValidation).isAttached();
        return this;
    }


//    @Step("Hover on 'For Me' menu")
//    public MainSteps hoverOnForMe() {
//        if (isMobile) {
//            mainPage.burgerMenu.click();
//        } else {
//            for (int i = 0; i < 3; i++) {
//                mainPage.forMeHover.hover();
//                if (mainPage.moneyTransferPage.isVisible()) break;
//            }
//        }
//        return this;
//    }
//
//    @Step("Navigate to Money Transfer page")
//    public MainSteps moneyTransferPage() {
//        if (isMobile) {
//            mainPage.otherProducts.click();
//            mainPage.moneyTransferPageMobile.click();
//        } else {
//            mainPage.moneyTransferPage.waitFor(new Locator.WaitForOptions()
//                    .setState(WaitForSelectorState.VISIBLE));
//            mainPage.moneyTransferPage.click();
//        }
//        return this;
//    }
//
//    @Step("Navigate to Loans page")
//    public MainSteps navigateToLoansPage() {
//        if (isMobile) {
//            mainPage.loansPageMobile.click();
//            mainPage.loansPageButton.click();
//        } else {
//            mainPage.loansPage.click();
//        }
//        return this;
//    }
//
//    @Step("Click on Consumer Loan page link")
//    public MainSteps clickOnLoansPage() {
//        mainPage.loanPageNavigate.click();
//        return this;
//    }
//
//    @Step("Navigate to Branches page")
//    public MainSteps clickBranchesPage() {
//        mainPage.branchesPage.click();
//        return this;
//    }
//
//    @Step("Navigate to Anonymous Tip page")
//    public MainSteps AnonTipPage() {
//        mainPage.anonTipPage.click();
//        return this;
//    }
//
//    @Step("Click on TBC Anonymous Tip protected page link")
//    public MainSteps clickTbcAnonPage() {
//        mainPage.anonTbcPage.click();
//        return this;
//    }
}