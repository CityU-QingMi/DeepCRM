    public void testFirstPeriodEqualsSecondPeriod() {

        Object[] a = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newTimestamp("2000-01-12 01:02:03")
        };
        Object[] b = {
            scanner.newTimestamp("1999-12-01 01:02:03"),
            scanner.newTimestamp("2000-01-12 01:02:03")
        };
        Type[] ta = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };
        Type[] tb = {
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0),
            new DateTimeType(Types.SQL_TIMESTAMP, Types.SQL_TIMESTAMP, 0)
        };

        Assert.assertTrue(DateTimeType.equals(session, a, ta, b, tb));
        Assert.assertTrue(DateTimeType.equals(session, b, tb, a, ta));
    }
