    public void testNestedSubselects() throws SQLException {

        String sql =
            "select name from trees where fruit_id in (select id from fruits where color_id in (select id from colors where val = 'red')) order by name";
        String[] expected = new String[] {
            "large macintosh tree", "large red delicious tree",
            "small red delicious tree"
        };

        compareResults(sql, expected, jdbcConnection);
    }
