    public ResultSet getProcedureColumns(String catalog, String schemaPattern,
            String procedureNamePattern,
            String columnNamePattern) throws SQLException {

        if (wantsIsNull(procedureNamePattern)
                || wantsIsNull(columnNamePattern)) {
            return executeSelect("SYSTEM_PROCEDURECOLUMNS", "0=1");
        }
        catalog       = translateCatalog(catalog);
        schemaPattern = translateSchema(schemaPattern);

        StringBuffer select = toQueryPrefix("SYSTEM_PROCEDURECOLUMNS").append(
            and("PROCEDURE_CAT", "=", catalog)).append(
            and("PROCEDURE_SCHEM", "LIKE", schemaPattern)).append(
            and("PROCEDURE_NAME", "LIKE", procedureNamePattern)).append(
            and("COLUMN_NAME", "LIKE", columnNamePattern));

        // By default, query already returns result ordered by
        // PROCEDURE_SCHEM and PROCEDURE_NAME...
        return execute(select.toString());
    }
