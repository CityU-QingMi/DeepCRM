    public void testAny() {

        try {
            String ddl =
                "drop table PRICE_RELATE_USER_ORDER_V2 if exists;"
                + "create table PRICE_RELATE_USER_ORDER_V2 "
                + "(ID_ORDER_V2 BIGINT, ID_USER NUMERIC, DATE_CREATE TIMESTAMP)";
            String sql = "insert into PRICE_RELATE_USER_ORDER_V2 "
                         + "(ID_ORDER_V2, ID_USER, DATE_CREATE) " + "values "
                         + "(?, ?, ?)";
            Statement st = connection.createStatement();

            st.execute(ddl);

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, 1);
            ps.setNull(2, Types.NUMERIC);
            ps.setTimestamp(
                3, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("TestSql.testAny() error: " + e.getMessage());
        }

        System.out.println("testAny complete");
    }
