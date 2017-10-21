    public void testFive() throws SQLException {

        Connection conn = newConnection();
        Statement  st   = conn.createStatement();

        st.execute(
            "create function func_table (in namep varchar(128)) returns table(cola varchar(128), colb varchar(128)) "
            + "return table(select schema_name, schema_owner from information_schema.schemata where schema_owner=namep);");

        CallableStatement cs = conn.prepareCall("call func_table('_SYSTEM')");
        boolean           isResult = cs.execute();

        assertTrue(isResult);

        ResultSet rs = cs.getResultSet();

        rs.next();
        assertEquals(rs.getString(1), "INFORMATION_SCHEMA");
        assertEquals(rs.getString(2), "_SYSTEM");
        rs.close();

        //
        isResult = st.execute("call func_table('_SYSTEM')");

        assertTrue(isResult);

        rs = st.getResultSet();

        rs.next();
        assertEquals(rs.getString(1), "INFORMATION_SCHEMA");
        assertEquals(rs.getString(2), "_SYSTEM");
        rs.close();
    }
