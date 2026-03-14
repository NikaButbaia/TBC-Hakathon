package ge.tbc.testautomation.data;

import com.github.javafaker.Faker;

import static ge.tbc.testautomation.data.Constants.*;

public class TestDataGenerator {

    private static final Faker faker = new Faker();

    public static String randomWord() {
        return faker.lorem().word();
    }

    public static String randomSentence() {
        return faker.lorem().sentence();
    }

    public static String randomReward() {
        return String.valueOf(faker.number().numberBetween(MIN_REWARD, MAX_REWARD));
    }

    public static int randomQuestionAmount() {
        return faker.number().numberBetween(MIN_QUESTION_AMOUNT, MAX_QUESTION_AMOUNT);
    }

    public static String randomTimeAmount() {
        return String.valueOf(faker.number().numberBetween(MIN_TIME, MAX_TIME));
    }
}