    String getDatabaseDefaultSchema() throws SQLException {

        final ResultSet rs = executeSelect("SYSTEM_SCHEMAS",
            "IS_DEFAULT=TRUE");

        String value = rs.next() ? rs.getString(1)
                         : null;
        rs.close();
        return value;
    }
