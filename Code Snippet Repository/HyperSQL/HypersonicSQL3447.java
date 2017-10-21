    public void testAliasScope() throws SQLException {

        String sql =
            "select a.val, b.name from sizes a, trees b where a.id = b.size_id and b.id in (select a.id from trees a, fruits b where a.fruit_id = b.id and b.name='red delicious') order by a.val";
        String[] expectedSizes = new String[] {
            "large", "small"
        };
        String[] expectedTrees = new String[] {
            "large red delicious tree", "small red delicious tree"
        };

        assertEquals(
            "Programmer error: expected arrays should be of equal length.",
            expectedSizes.length, expectedTrees.length);

        Statement statement = jdbcConnection.createStatement();
        ResultSet results   = statement.executeQuery(sql);
        int       rowCount  = 0;

        while (results.next()) {
            assertTrue("Statement <" + sql + "> returned too many rows.",
                       (rowCount < expectedSizes.length));
            assertEquals("Statement <" + sql + "> returned wrong value.",
                         expectedSizes[rowCount], results.getString(1));
            assertEquals("Statement <" + sql + "> returned wrong value.",
                         expectedTrees[rowCount], results.getString(2));

            rowCount++;
        }

        assertEquals("Statement <" + sql + "> returned wrong number of rows.",
                     expectedSizes.length, rowCount);
    }
