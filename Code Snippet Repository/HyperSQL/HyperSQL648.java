    public ResultSet getBestRowIdentifier(String catalog, String schema,
            String table, int scope, boolean nullable) throws SQLException {

        if (table == null) {
            throw JDBCUtil.nullArgument("table");
        }

        String scopeIn;

        switch (scope) {

            case bestRowTemporary :
                scopeIn = BRI_TEMPORARY_SCOPE_IN_LIST;

                break;
            case bestRowTransaction :
                scopeIn = BRI_TRANSACTION_SCOPE_IN_LIST;

                break;
            case bestRowSession :
                scopeIn = BRI_SESSION_SCOPE_IN_LIST;

                break;
            default :
                throw JDBCUtil.invalidArgument("scope");
        }
        catalog = translateCatalog(catalog);
        schema  = translateSchema(schema);

        Integer Nullable = (nullable) ? null
                                      : INT_COLUMNS_NO_NULLS;
        StringBuffer select =
            toQueryPrefix("SYSTEM_BESTROWIDENTIFIER").append(and("TABLE_CAT",
                "=", catalog)).append(and("TABLE_SCHEM", "=",
                    schema)).append(and("TABLE_NAME", "=",
                                        table)).append(and("NULLABLE", "=",
                                            Nullable)).append(" AND SCOPE IN "
                                                + scopeIn);

        // By default, query already returns rows in contract order.
        // However, the way things are set up, there should never be
        // a result where there is > 1 distinct scope value:  most requests
        // will want only one table and the system table producer (for
        // now) guarantees that a maximum of one BRI scope column set is
        // produced for each table
        return execute(select.toString());
    }
