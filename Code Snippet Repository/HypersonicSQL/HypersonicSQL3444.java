    public void testAndedSubselects() throws SQLException {

        String sql =
            "select name from trees where size_id in (select id from sizes where val = 'large') and fruit_id in (select id from fruits where color_id = 1) order by name";
        String[] expected = new String[] {
            "large macintosh tree", "large red delicious tree"
        };

        compareResults(sql, expected, jdbcConnection);
    }
