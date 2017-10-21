    private static void compareResults(String sql, String[] expected,
                                       Connection jdbcConnection)
                                       throws SQLException {

        Statement statement = jdbcConnection.createStatement();
        ResultSet results   = statement.executeQuery(sql);
        int       rowCount  = 0;

        while (results.next()) {
            assertTrue("Statement <" + sql + "> returned too many rows.",
                       (rowCount < expected.length));
            assertEquals("Statement <" + sql + "> returned wrong value.",
                         expected[rowCount], results.getString(1));

            rowCount++;
        }

        assertEquals("Statement <" + sql + "> returned wrong number of rows.",
                     expected.length, rowCount);
    }
