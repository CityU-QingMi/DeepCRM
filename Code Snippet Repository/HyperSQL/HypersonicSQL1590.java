    public ResultSet getSuperTables(
            String catalog, String schemaPattern,
            String tableNamePattern) throws SQLException {

        // query with no result
        StringBuffer select = toQueryPrefixNoSelect(
            "SELECT TABLE_NAME AS TABLE_CAT, TABLE_NAME AS TABLE_SCHEM, TABLE_NAME, TABLE_NAME AS SUPERTABLE_NAME "
            + "FROM INFORMATION_SCHEMA.TABLES ").append(
                and("TABLE_NAME", "=", ""));

        return execute(select.toString());
    }
