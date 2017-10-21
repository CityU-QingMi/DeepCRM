    public void testSameTable() throws SQLException {

        String sql =
            "select name from trees where id in (select id from trees where fruit_id = 3) order by name";
        String[] expected = new String[] {
            "large red delicious tree", "small red delicious tree"
        };

        compareResults(sql, expected, jdbcConnection);
    }
