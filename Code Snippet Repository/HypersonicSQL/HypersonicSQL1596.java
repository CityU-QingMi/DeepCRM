    public ResultSet getFunctions(
            String catalog, String schemaPattern,
            String functionNamePattern) throws SQLException {

        StringBuffer sb = new StringBuffer(256);

        sb.append("select ").append(
            "sp.procedure_cat as FUNCTION_CAT,").append(
            "sp.procedure_schem as FUNCTION_SCHEM,").append(
            "sp.procedure_name as FUNCTION_NAME,").append(
            "sp.remarks as REMARKS,").append("1 as FUNCTION_TYPE,").append(
            "sp.specific_name as SPECIFIC_NAME ").append(
            "from information_schema.system_procedures sp ").append(
            "where sp.procedure_type = 2 ");

        if (wantsIsNull(functionNamePattern)) {
            return execute(sb.append("and 1=0").toString());
        }
        schemaPattern = translateSchema(schemaPattern);

        sb.append(and("sp.procedure_cat", "=",
                      catalog)).append(and("sp.procedure_schem", "LIKE",
                          schemaPattern)).append(and("sp.procedure_name",
                              "LIKE", functionNamePattern));

        // By default, query already returns the result ordered by
        // FUNCTION_SCHEM, FUNCTION_NAME...
        return execute(sb.toString());
    }
