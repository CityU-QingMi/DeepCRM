    public void testWhereClausesColliding() throws SQLException {

        String sql =
            "select name from fruits where id in (select fruit_id from trees where id < 3) order by name";
        String[] expected = new String[] {
            "golden delicious", "macintosh"
        };

        compareResults(sql, expected, jdbcConnection);
    }
