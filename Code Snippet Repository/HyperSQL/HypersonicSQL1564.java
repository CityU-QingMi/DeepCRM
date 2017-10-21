    public void setSchema(String schema) throws SQLException {

        checkClosed();

        if (schema == null) {
            throw JDBCUtil.nullArgument("schema");
        } else if (schema.length() == 0) {
            throw JDBCUtil.invalidArgument("Zero-length schema");
        } else {
            (new JDBCDatabaseMetaData(this)).setConnectionDefaultSchema(
                schema);
        }
    }
