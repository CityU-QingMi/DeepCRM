    public void testFirstPeriodDoesNotOverlapsSecondPeriodWithInterval() {

        Object[] a = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newInterval(
                "31",
                IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0))
        };
        Object[] b = {
            scanner.newTimestamp("2000-01-01 01:02:03"),
            scanner.newTimestamp("2000-02-01 01:02:03")
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertFalse(DateTimeType.overlaps(session, a, ta, b, tb));
    }
