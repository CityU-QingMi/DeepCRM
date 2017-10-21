    private String translateCatalog(String catalogName) throws SQLException {

        if (useSchemaDefault && catalogName != null
                && catalogName.length() == 0) {
            String result = getDatabaseDefaultCatalog();

            if (result != null) {
                catalogName = result;
            }
        }

        return catalogName;
    }
