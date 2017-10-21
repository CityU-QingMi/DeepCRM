    public static void procWithResultTwo(Connection conn, Integer[] intparam,
                                         ResultSet[] resultparamOne,
                                         ResultSet[] resultparamTwo)
                                         throws SQLException {

        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
            "select count(*) from information_schema.columns where table_name='LOB_IDS' and table_schema='SYSTEM_LOBS'");

        if (rs.next()) {
            intparam[0] = rs.getInt(1);

            rs.close();
        }

        resultparamOne[0] = st.executeQuery(
            "select table_schema, table_name from information_schema.tables where table_name='LOB_IDS' and table_schema='SYSTEM_LOBS'");
        resultparamTwo[0] = st.executeQuery(
            "select table_schema, table_name from information_schema.tables where table_name='LOBS' and table_schema='SYSTEM_LOBS'");
    }
