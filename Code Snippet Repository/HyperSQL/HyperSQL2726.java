    public void testHiddenCollision() throws SQLException {

        String sql =
            "select name from fruits where id in (select fruit_id from trees) order by name";
        String[] expected = new String[] {
            "golden delicious", "granny smith", "macintosh", "red delicious"
        };

        compareResults(sql, expected, jdbcConnection);
    }
