    public void testSameTableWithAliases() throws SQLException {

        String sql =
            "select a.name from trees a where a.id in (select b.id from trees b where b.fruit_id = 3) order by a.name";
        String[] expected = new String[] {
            "large red delicious tree", "small red delicious tree"
        };

        compareResults(sql, expected, jdbcConnection);
    }
