    private String getDataSourceSpec(String tableName) {

        String spec = null;

        try {
            java.sql.ResultSet results = m_statement.executeQuery(
                "SELECT DATA_SOURCE_DEFINTION FROM INFORMATION_SCHEMA.SYSTEM_TEXTTABLES "
                + "WHERE TABLE_NAME='" + tableName + "'");

            results.next();

            spec = results.getString(1);
        } catch (SQLException ex) {
            fail("getDataSourceSpec(" + tableName + ") failed: "
                 + ex.toString());
        }

        return spec;
    }
