    public void testDateRangeCheck() throws SQLException {

        Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:db", "sa",
            "");
        Statement stmt = c.createStatement();

        stmt.execute("create table testdate (d date)");
        stmt.executeUpdate("insert into testdate values DATE'2017-01-19'");

        PreparedStatement pstmt =
            c.prepareStatement("insert into testdate values ?");

        try {
            pstmt.setDate(1, new Date(25000, 1, 1));
            pstmt.executeUpdate();
            fail("invalid date beyond 9999CE accepted");
        } catch (SQLException e) {}
    }
