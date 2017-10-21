    public void testSameTableWithJoin() throws SQLException {

        String sql =
            "select sizes.val from trees, sizes where sizes.id = trees.size_id and trees.id in (select id from trees where fruit_id = 3) order by sizes.val";
        String[] expected = new String[] {
            "large", "small"
        };

        compareResults(sql, expected, jdbcConnection);
    }
