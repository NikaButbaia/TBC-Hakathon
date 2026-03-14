package ge.tbc.testautomation.api.data.constants;

public class Constants {

    public static class URI {
        public static final String BASE = "http://63.180.229.216:5118";
    }

    public static class Paths {
        public static final String QUESTS   = "/api/Quests";
        public static final String QUIZZES  = "/api/Quizzes";
        public static final String USERS    = "/api/Users";
        public static final String WALLET   = "/api/Wallet";
    }

    public static class Endpoints {
        public static final String QUEST_BY_ID       = "/{id}";
        public static final String QUEST_RESOLVE     = "/{id}/resolve";
        public static final String QUEST_COMPLETE    = "/{id}/complete";
        public static final String QUIZ_BY_ID        = "/{id}";
        public static final String QUIZ_SUBMIT       = "/{id}/submit";
        public static final String QUIZ_RESOLVE      = "/{id}/resolve";
        public static final String ME                = "/me";
        public static final String FAMILY_MEMBERS    = "/family-members";
        public static final String DASHBOARD         = "/dashboard";
    }

    public static class Params {
        public static final String ID         = "id";
        public static final String APPROVE    = "approve";
        public static final String ACTIVE_ONLY = "activeOnly";
    }

    public static class QuestTypes {
        public static final int DAILY  = 1;
        public static final int WEEKLY = 2;
        public static final int CUSTOM = 3;
    }

    public static class TestData {
        public static final String PARENT_USER_ID    = "";
        public static final String CHILD_USER_ID     = "";
        public static final double VALID_REWARD      = 10.0;
        public static final double ZERO_REWARD       = 0.0;
        public static final double NEGATIVE_REWARD   = -10.0;
        public static final double HIGH_REWARD       = 50.0;
        public static final double QUIZ_REWARD       = 15.0;
        public static final int    INVALID_QUEST_TYPE = 999;
        public static final String NON_EXISTENT_UUID = "00000000-0000-0000-0000-000000000000";
        public static final String MALFORMED_UUID    = "not-a-uuid";
    }
}