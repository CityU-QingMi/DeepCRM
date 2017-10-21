    public ResultSet getIndexInfo(String catalog, String schema, String table,
                                  boolean unique,
                                  boolean approximate) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        Boolean nu = (unique) ? Boolean.FALSE
                              : null;
        StringBuffer select =
            toQueryPrefix("SYSTEM_INDEXINFO").append(and("TABLE_CAT", "=",
                catalog)).append(and("TABLE_SCHEM", "=",
                                     schema)).append(and("TABLE_NAME", "=",
                                         table)).append(and("NON_UNIQUE", "=",
                                             nu));

        // By default, this query already returns the table ordered by
        // NON_UNIQUE, TYPE, INDEX_NAME, and ORDINAL_POSITION...
        return execute(select.toString());
    }
