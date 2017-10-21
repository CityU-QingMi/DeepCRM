    public ResultSet getPrimaryKeys(String catalog, String schema,
                                    String table) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        StringBuffer select =
            toQueryPrefix("SYSTEM_PRIMARYKEYS").append(and("TABLE_CAT", "=",
                catalog)).append(and("TABLE_SCHEM", "=",
                                     schema)).append(and("TABLE_NAME", "=",
                                         table));

        // By default, query already returns result in contract order
        return execute(select.toString());
    }
