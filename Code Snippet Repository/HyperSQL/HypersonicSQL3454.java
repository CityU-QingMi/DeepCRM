    public void testWhereSelectCollidingWithAliases() throws SQLException {

        // Yes, this is a nonsensical query
        String sql =
            "select a.val from colors a where a.id in (select b.id from trees b where b.fruit_id = 3) order by a.val";
        String[] expected = new String[] {
            "indigo", "orange"
        };

        compareResults(sql, expected, jdbcConnection);
    }
