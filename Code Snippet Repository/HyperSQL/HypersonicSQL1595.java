    public ResultSet getSchemas(String catalog,
                                String schemaPattern) throws SQLException {

        StringBuffer select =
            toQueryPrefix("SYSTEM_SCHEMAS").append(and("TABLE_CATALOG", "=",
                catalog)).append(and("TABLE_SCHEM", "LIKE", schemaPattern));

        // By default, query already returns result in contract order
        return execute(select.toString());
    }
