    public static void procWithResultOne(Integer[] intparam,
                                         ResultSet[] resultparam)
                                         throws SQLException {

        Connection conn =
            DriverManager.getConnection("jdbc:default:connection");

        conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
            "select count(*) from information_schema.columns where table_name='LOB_IDS' and table_schema='SYSTEM_LOBS'");

        if (rs.next()) {
            intparam[0] = rs.getInt(1);

            rs.close();
        }

        resultparam[0] = st.executeQuery(
            "select table_schema, table_name from information_schema.tables where table_name='LOB_IDS' and table_schema='SYSTEM_LOBS'");
    }
