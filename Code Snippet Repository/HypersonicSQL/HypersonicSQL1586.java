    public ResultSet getCrossReference(
            String parentCatalog, String parentSchema, String parentTable,
            String foreignCatalog, String foreignSchema,
            String foreignTable) throws SQLException {

        if (parentTable == null) {
            throw JDBCUtil.nullArgument("parentTable");
        }

        if (foreignTable == null) {
            throw JDBCUtil.nullArgument("foreignTable");
        }
        parentCatalog  = translateCatalog(parentCatalog);
        foreignCatalog = translateCatalog(foreignCatalog);
        parentSchema   = translateSchema(parentSchema);
        foreignSchema  = translateSchema(foreignSchema);

        StringBuffer select =
            toQueryPrefix("SYSTEM_CROSSREFERENCE").append(and("PKTABLE_CAT",
                "=", parentCatalog)).append(and("PKTABLE_SCHEM", "=",
                    parentSchema)).append(and("PKTABLE_NAME", "=",
                        parentTable)).append(and("FKTABLE_CAT", "=",
                            foreignCatalog)).append(and("FKTABLE_SCHEM", "=",
                                foreignSchema)).append(and("FKTABLE_NAME",
                                    "=", foreignTable));

        // by default, query already returns the table ordered by
        // FKTABLE_CAT, FKTABLE_SCHEM, FKTABLE_NAME, and KEY_SEQ.
        return execute(select.toString());
    }
