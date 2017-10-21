    public void testAggregatedGroupBy() throws SQLException {

        String sql = "select avg(salary), max(id) from employee "
                     + "group by superior_id " + "order by superior_id " + "";
        Object[][] expected = new Object[][] {
            {
                new Double(150000), new Integer(2)
            }, {
                new Double(42500), new Integer(13)
            }, {
                new Double(45000), new Integer(23)
            },
        };

        compareResults(sql, expected, "00000");
    }
