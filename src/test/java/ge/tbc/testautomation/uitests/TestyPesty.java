package ge.tbc.testautomation.uitests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class TestyPesty extends BaseTest {

    Faker faker = new Faker();

    @Test
    public void TestyTesty() {
        parentalControlsSteps
                .openQuest()
                .giveTaskName(faker.lorem().word())
                .selectTask(2)
                .typeDescription(faker.lorem().sentence())
                .inputStartDate()
                .selectDay(2)
                .inputEndDate()
                .selectDay(19)
                .inputRewards(String.valueOf(faker.number().numberBetween(1, 100)))
                .selectSecondDropdown(1)
                .clickNextBtn()
                .inputQuestionTimeAmount(
                String.valueOf(faker.number().numberBetween(1, 100)),
                String.valueOf(faker.number().numberBetween(1, 100)));
    }
}