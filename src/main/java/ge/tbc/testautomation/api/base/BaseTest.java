package ge.tbc.testautomation.api.base;

import ge.tbc.testautomation.api.steps.BankSteps;
import ge.tbc.testautomation.api.steps.ChildSteps;
import ge.tbc.testautomation.api.steps.ParentSteps;
import ge.tbc.testautomation.api.steps.QuizSteps;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ParentSteps parentSteps;
    protected ChildSteps childSteps;
    protected QuizSteps quizSteps;
    protected BankSteps bankSteps;

    @BeforeClass
    public void setUp() {
        parentSteps = new ParentSteps();
        childSteps  = new ChildSteps();
        quizSteps   = new QuizSteps();
        bankSteps   = new BankSteps();
    }
}