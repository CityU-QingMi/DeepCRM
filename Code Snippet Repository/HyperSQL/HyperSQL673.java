    String getConnectionDefaultSchema() throws SQLException {

        ResultSet rs = execute("CALL CURRENT_SCHEMA");

        rs.next();

        String result = rs.getString(1);

        rs.close();

        return result;
    }
