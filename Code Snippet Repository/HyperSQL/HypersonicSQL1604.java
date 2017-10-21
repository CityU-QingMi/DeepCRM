    private String translateSchema(String schemaName) throws SQLException {

        if (useSchemaDefault && schemaName != null
                && schemaName.length() == 0) {
            final String result = getDatabaseDefaultSchema();

            if (result != null) {
                schemaName = result;
            }
        }

        return schemaName;
    }
