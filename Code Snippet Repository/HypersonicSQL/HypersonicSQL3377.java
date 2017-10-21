    public void testFirstPeriodDoesNotImmediatelySucceedsSecondPeriod() {

        Object[] a = {
            scanner.newTimestamp("2000-02-01 01:02:03"),
            scanner.newTimestamp("2000-03-01 01:02:03")
        };
        Object[] b = {
            scanner.newTimestamp("2000-01-01 01:02:04"),
            scanner.newTimestamp("2000-02-01 01:02:03")
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertTrue(DateTimeType.immediatelySucceeds(session, a, ta, b,
                tb));
    }
