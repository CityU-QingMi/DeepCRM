    public ResultSet getColumns(String catalog, String schemaPattern,
                                String tableNamePattern,
                                String columnNamePattern) throws SQLException {

        if (wantsIsNull(tableNamePattern) || wantsIsNull(columnNamePattern)) {
            return executeSelect("SYSTEM_COLUMNS", "0=1");
        }
        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        StringBuffer select = toQueryPrefix("SYSTEM_COLUMNS").append(
            and("TABLE_CAT", "=", catalog)).append(
            and("TABLE_SCHEM", "LIKE", schemaPattern)).append(
            and("TABLE_NAME", "LIKE", tableNamePattern)).append(
            and("COLUMN_NAME", "LIKE", columnNamePattern));

        // by default, query already returns the result ordered
        // by TABLE_SCHEM, TABLE_NAME and ORDINAL_POSITION
        return execute(select.toString());
    }
