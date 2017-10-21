    public void testThree() throws SQLException {

        Connection conn = newConnection();
        Statement  st   = conn.createStatement();

        st.execute("declare varone int default 0;");
        st.execute(
            "create procedure proc_inout_result (inout intp int) "
            + " language java reads sql data external name 'CLASSPATH:org.hsqldb.test.TestStoredProcedure.procWithResultOne'");

        CallableStatement cs =
            conn.prepareCall("call proc_inout_result(varone)");
        boolean isResult = cs.execute();

        assertFalse(isResult);
        cs.getMoreResults();

        ResultSet rs = cs.getResultSet();

        rs.next();
        assertEquals(rs.getString(1), "SYSTEM_LOBS");
        assertEquals(rs.getString(2), "LOB_IDS");
        rs.close();
    }
