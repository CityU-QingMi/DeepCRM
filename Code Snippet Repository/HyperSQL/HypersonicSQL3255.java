    public static void main(String[] argv) {

        TestDatetimeSimple testA = new TestDatetimeSimple();
        String[]           zones = { "GMT+05:00", "GMT", "GMT-05:00" };

        try {
            for (int i = 0; i < zones.length; i++) {
                TimeZone timeZone = TimeZone.getTimeZone(zones[i]);

                TimeZone.setDefault(timeZone);
                testA.testSimple();
                testA.testValues();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
