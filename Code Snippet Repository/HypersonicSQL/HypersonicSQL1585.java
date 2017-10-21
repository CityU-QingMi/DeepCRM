    public ResultSet getExportedKeys(String catalog, String schema,
                                     String table) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        StringBuffer select =
            toQueryPrefix("SYSTEM_CROSSREFERENCE").append(and("PKTABLE_CAT",
                "=", catalog)).append(and("PKTABLE_SCHEM", "=",
                    schema)).append(and("PKTABLE_NAME", "=", table));

        // By default, query already returns the table ordered by
        // FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, and KEY_SEQ.
        return execute(select.toString());
    }
