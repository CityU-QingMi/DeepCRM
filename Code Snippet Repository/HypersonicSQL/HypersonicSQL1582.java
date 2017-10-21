    public ResultSet getVersionColumns(String catalog, String schema,
                                       String table) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        StringBuffer select =
            toQueryPrefix("SYSTEM_VERSIONCOLUMNS").append(and("TABLE_CAT",
                "=", catalog)).append(and("TABLE_SCHEM", "=",
                    schema)).append(and("TABLE_NAME", "=", table));

        // result does not need to be ordered
        return execute(select.toString());
    }
