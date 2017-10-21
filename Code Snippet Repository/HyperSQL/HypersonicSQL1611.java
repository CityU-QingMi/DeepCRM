    public ResultSet getColumnPrivileges(String catalog, String schema,
            String table, String columnNamePattern) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
/**/
/**/
/**/
/**/
/**/
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        String sql =
            "SELECT TABLE_CATALOG TABLE_CAT, TABLE_SCHEMA TABLE_SCHEM,"
            + "TABLE_NAME, COLUMN_NAME, GRANTOR, GRANTEE, PRIVILEGE_TYPE PRIVILEGE, IS_GRANTABLE "
            + "FROM INFORMATION_SCHEMA.COLUMN_PRIVILEGES WHERE TRUE "
            + and("TABLE_CATALOG", "=", catalog)
            + and("TABLE_SCHEMA", "=", schema) + and("TABLE_NAME", "=", table)
            + and("COLUMN_NAME", "LIKE", columnNamePattern)
        ;

        // By default, the query already returns the result
        // ordered by column name, privilege...
        return execute(sql);
    }
