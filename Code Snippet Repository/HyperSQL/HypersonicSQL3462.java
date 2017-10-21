    private boolean isReadOnly(String tableName) {

        boolean isReadOnly = true;

        try {
            java.sql.ResultSet systemTables = m_statement.executeQuery(
                "SELECT READ_ONLY FROM INFORMATION_SCHEMA.SYSTEM_TABLES "
                + "WHERE TABLE_NAME='" + m_products.getName() + "'");

            systemTables.next();

            isReadOnly = systemTables.getBoolean(1);
        } catch (SQLException ex) {
            fail("isReadOnly(" + tableName + ") failed: " + ex.toString());
        }

        return isReadOnly;
    }
