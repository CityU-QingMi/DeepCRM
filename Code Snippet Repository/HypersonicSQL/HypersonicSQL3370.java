    public void testFirstPeriodContainsItselfMinus1Second() {

        Object[] a = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newTimestamp("1999-12-31 01:02:03")
        };
        Object[] b = {
            scanner.newTimestamp("1999-12-31 01:02:02"),
            scanner.newTimestamp("1999-12-31 01:02:03")
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertTrue(DateTimeType.contains(session, a, ta, b, tb, false));
    }
