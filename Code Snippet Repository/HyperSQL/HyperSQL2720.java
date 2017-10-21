    public void testNotInSameTableAndColumn() throws SQLException {

        String sql =
            "select name from fruits where id not in (select id from fruits where color_id > 1 ) order by name";
        String[] expected = new String[] {
            "macintosh", "red delicious"
        };

        compareResults(sql, expected, jdbcConnection);
    }
