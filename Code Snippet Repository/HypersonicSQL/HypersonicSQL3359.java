    public void testFirstPeriodDoesNotSucceedsSecondPeriod2() {

        Object[] a = {
            scanner.newTimestamp("2000-02-01 01:02:03"),
            scanner.newTimestamp("2000-03-01 01:02:03")
        };
        Object[] b = {
            scanner.newTimestamp("2000-01-01 01:02:03"),
            scanner.newTimestamp("2000-02-01 01:02:04")
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertFalse(DateTimeType.succeeds(session, a, ta, b, tb));
    }
