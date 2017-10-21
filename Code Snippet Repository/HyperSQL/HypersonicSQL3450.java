    public void testWhereClausesCollidingWithAliases() throws SQLException {

        String sql =
            "select a.name from fruits a where a.id in (select b.fruit_id from trees b where b.id < 3) order by name";
        String[] expected = new String[] {
            "golden delicious", "macintosh"
        };

        compareResults(sql, expected, jdbcConnection);
    }
