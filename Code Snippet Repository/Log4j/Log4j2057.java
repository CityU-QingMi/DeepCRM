    @Parameterized.Parameters
    public static Object[][] data() {
        final long millis = System.currentTimeMillis();
        return new Object[][]{
            {Date.class, millis, new Date(millis)},
            {java.sql.Date.class, millis, new java.sql.Date(millis)},
            {Time.class, millis, new Time(millis)},
            {Timestamp.class, millis, new Timestamp(millis)}
        };
    }
