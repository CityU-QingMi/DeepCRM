    public static ResultSet getCustomResult(Connection connection, long start,
            long end) throws SQLException {

        Result result = newTwoColumnResult();

        if (end < start) {
            long temp = start;

            start = end;
            end   = temp;
        }

        if (end > 1000) {
            throw org.hsqldb.jdbc.JDBCUtil.invalidArgument(
                "value larger than 100");
        }

        if (end > start + 100) {
            end = start + 100;
        }

        for (long i = start; i < end; i++) {
            Object[] row = new Object[2];

            row[0] = Long.valueOf(i);
            row[1] = new BinaryData(BigInteger.valueOf(i).toByteArray(),
                                    false);

            result.navigator.add(row);
        }

        result.navigator.reset();

        return new JDBCResultSet((JDBCConnection) connection, null, result,
                                 result.metaData);
    }
