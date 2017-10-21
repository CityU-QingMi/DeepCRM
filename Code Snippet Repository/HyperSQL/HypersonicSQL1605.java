    String getDatabaseDefaultCatalog() throws SQLException {

        final ResultSet rs = executeSelect("SYSTEM_SCHEMAS",
            "IS_DEFAULT=TRUE");

        String value = rs.next() ? rs.getString(2)
                         : null;
        rs.close();
        return value;
    }
