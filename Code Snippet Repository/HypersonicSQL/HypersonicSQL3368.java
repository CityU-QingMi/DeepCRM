    public void testFirstPeriodDoesNotContainsItsEndDate() {

        Object[] a = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newTimestamp("1999-12-31 01:02:03")
        };
        Object[] b = {
            scanner.newTimestamp("1999-12-31 01:02:03"),
            null
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertFalse(DateTimeType.contains(session, a, ta, b, tb,
                true));
    }
