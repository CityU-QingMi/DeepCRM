    public void testHiddenCollisionWithAliases() throws SQLException {

        String sql =
            "select a.name from fruits a where a.id in (select b.fruit_id from trees b) order by a.name";
        String[] expected = new String[] {
            "golden delicious", "granny smith", "macintosh", "red delicious"
        };

        compareResults(sql, expected, jdbcConnection);
    }
