    public void testWhereSelectColliding() throws SQLException {

        // Yes, this is a nonsensical query
        String sql =
            "select val from colors where id in (select id from trees where fruit_id = 3) order by val";
        String[] expected = new String[] {
            "indigo", "orange"
        };

        compareResults(sql, expected, jdbcConnection);
    }
