    public ResultSet getProcedures(
            String catalog, String schemaPattern,
            String procedureNamePattern) throws SQLException {

        if (wantsIsNull(procedureNamePattern)) {
            return executeSelect("SYSTEM_PROCEDURES", "0=1");
        }
        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        StringBuffer select =
            toQueryPrefix("SYSTEM_PROCEDURES").append(and("PROCEDURE_CAT",
                "=", catalog)).append(and("PROCEDURE_SCHEM", "LIKE",
                    schemaPattern)).append(and("PROCEDURE_NAME", "LIKE",
                        procedureNamePattern));

        // By default, query already returns the result ordered by
        // PROCEDURE_SCHEM, PROCEDURE_NAME...
        return execute(select.toString());
    }
