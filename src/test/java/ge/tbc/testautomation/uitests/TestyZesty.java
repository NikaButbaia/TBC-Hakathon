package ge.tbc.testautomation.uitests;

import org.testng.annotations.Test;

public class TestyZesty extends BaseTest{
    @Test
    public void testNameChangesOnForward(){
        parentalControlsSteps.assertNameChanged();
    }
}
