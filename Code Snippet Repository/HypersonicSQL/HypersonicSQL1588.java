    public ResultSet getUDTs(String catalog, String schemaPattern,
                             String typeNamePattern,
                             int[] types) throws SQLException {

        if (wantsIsNull(typeNamePattern)
                || (types != null && types.length == 0)) {
            executeSelect("SYSTEM_UDTS", "0=1");
        }
        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        StringBuffer select =
            toQueryPrefix("SYSTEM_UDTS").append(and("TYPE_CAT", "=",
                catalog)).append(and("TYPE_SCHEM", "LIKE",
                                     schemaPattern)).append(and("TYPE_NAME",
                                         "LIKE", typeNamePattern));

        if (types == null) {

            // do not use to narrow search
        } else {
            select.append(" AND DATA_TYPE IN (").append(
                StringUtil.getList(types, ",", "")).append(')');
        }

        // By default, the query already returns a result ordered by
        // DATA_TYPE, TYPE_SCHEM, and TYPE_NAME...
        return execute(select.toString());
    }
