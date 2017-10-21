    public void testFour() throws SQLException {

        Connection conn = newConnection();
        Statement  st   = conn.createStatement();

        st.execute("declare varone int default 0;");
        st.execute(
            "create procedure proc_inout_result_two (inout intp int) "
            + " language java reads sql data dynamic result sets 2 external name 'CLASSPATH:org.hsqldb.test.TestStoredProcedure.procWithResultTwo'");

        CallableStatement cs =
            conn.prepareCall("call proc_inout_result_two(varone)");
        boolean isResult = cs.execute();

        assertFalse(isResult);
        cs.getMoreResults();

        ResultSet rs = cs.getResultSet();

        rs.next();
        assertEquals(rs.getString(1), "SYSTEM_LOBS");
        assertEquals(rs.getString(2), "LOB_IDS");
        rs.close();

        if (cs.getMoreResults()) {
            rs = cs.getResultSet();

            rs.next();
            assertEquals(rs.getString(1), "SYSTEM_LOBS");
            assertEquals(rs.getString(2), "LOBS");
            rs.close();
        }
    }
