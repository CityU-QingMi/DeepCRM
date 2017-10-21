    public void testHavingWithoutGroupBy1() throws SQLException {

        String sql = "select avg(salary), max(id) from employee "
                     + "having avg(salary) > 1000 " + "";
        Object[][] expected = new Object[][] {
            {
                new Double(65000), new Integer(23)
            },
        };

        compareResults(sql, expected, "00000");
    }
