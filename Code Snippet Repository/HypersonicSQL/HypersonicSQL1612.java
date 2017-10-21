    public ResultSet getTablePrivileges(
            String catalog, String schemaPattern,
            String tableNamePattern) throws SQLException {

        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        String sql =
            "SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM,"
            + "TABLE_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE "
            + "FROM INFORMATION_SCHEMA.TABLE_PRIVILEGES WHERE TRUE "
            + and("TABLE_CATALOG", "=", catalog)
            + and("TABLE_SCHEMA", "LIKE", schemaPattern)
            + and("TABLE_NAME", "LIKE", tableNamePattern);

/**/
/**/
/**/
/**/
/**/

        // By default, the query already returns a result ordered by
        // TABLE_SCHEM, TABLE_NAME, and PRIVILEGE...
        return execute(sql);
    }
