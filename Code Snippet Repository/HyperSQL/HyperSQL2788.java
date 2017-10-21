    private String getViewStatement(String viewName) throws SQLException {

        ResultSet res = m_statement.executeQuery(
            "SELECT VIEW_DEFINITION FROM INFORMATION_SCHEMA.VIEWS WHERE TABLE_NAME = '"
            + viewName + "'");

        res.next();

        String statement = res.getString(1);

        return statement;
    }
