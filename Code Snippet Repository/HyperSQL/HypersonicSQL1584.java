    public ResultSet getImportedKeys(String catalog, String schema,
                                     String table) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        StringBuffer select = toQueryPrefix("SYSTEM_CROSSREFERENCE").append(
            and("FKTABLE_CAT", "=", catalog)).append(
            and("FKTABLE_SCHEM", "=", schema)).append(
            and("FKTABLE_NAME", "=", table)).append(
            " ORDER BY PKTABLE_CAT, PKTABLE_SCHEM, PKTABLE_NAME, KEY_SEQ");

        return execute(select.toString());
    }
