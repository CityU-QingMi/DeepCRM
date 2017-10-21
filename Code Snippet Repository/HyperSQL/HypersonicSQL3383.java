    public void testPeriodStartingWithInterval() {

        Object[] a = {
            scanner.newInterval(
                "31",
                IntervalType.newIntervalType(Types.SQL_INTERVAL_DAY, 2, 0)),
            scanner.newTimestamp("1999-12-01 01:02:03")
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

        try {
            DateTimeType.overlaps(null, a, ta, b, tb);
            Assert.fail("It is illegal to start a period with an interval");
        } catch (Exception e) {}
    }
