    public void testFirstPeriodIsAfterSecondPeriodWithInterval() {

        Object[] a = {
            scanner.newTimestamp("2000-01-01 01:02:03"),
            scanner.newInterval(
                "7",
                IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0))
        };
        Object[] b = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newInterval(
                "7",
                IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0))
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0)
        };

        Assert.assertFalse(DateTimeType.precedes(session, a, ta, b, tb));
    }
