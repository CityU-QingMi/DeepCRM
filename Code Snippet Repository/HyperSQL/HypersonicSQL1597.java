    public ResultSet getFunctionColumns(
            String catalog, String schemaPattern, String functionNamePattern,
            String columnNamePattern) throws SQLException {

        StringBuffer sb = new StringBuffer(256);

        sb.append("select pc.procedure_cat as FUNCTION_CAT,").append(
            "pc.procedure_schem as FUNCTION_SCHEM,").append(
            "pc.procedure_name as FUNCTION_NAME,").append(
            "pc.column_name as COLUMN_NAME,").append(
            "case pc.column_type").append(" when 3 then 5").append(
            " when 4 then 3").append(" when 5 then 4").append(
            " else pc.column_type").append(" end as COLUMN_TYPE,").append(
            "pc.DATA_TYPE,").append("pc.TYPE_NAME,").append(
            "pc.PRECISION,").append("pc.LENGTH,").append("pc.SCALE,").append(
            "pc.RADIX,").append("pc.NULLABLE,").append("pc.REMARKS,").append(
            "pc.CHAR_OCTET_LENGTH,").append("pc.ORDINAL_POSITION,").append(
            "pc.IS_NULLABLE,").append("pc.SPECIFIC_NAME,").append(
            "case pc.column_type").append(" when 3 then 1").append(
            " else 0").append(" end AS COLUMN_GROUP ").append(
            "from information_schema.system_procedurecolumns pc ").append(
            "join (select procedure_schem,").append("procedure_name,").append(
            "specific_name ").append(
            "from information_schema.system_procedures ").append(
            "where procedure_type = 2) p ").append(
            "on pc.procedure_schem = p.procedure_schem ").append(
            "and pc.procedure_name = p.procedure_name ").append(
            "and pc.specific_name = p.specific_name ").append(
            "and ((pc.column_type = 3 and pc.column_name = '@p0') ").append(
            "or ").append("(pc.column_type <> 3)) ");

        if (wantsIsNull(functionNamePattern)
                || wantsIsNull(columnNamePattern)) {
            return execute(sb.append("where 1=0").toString());
        }
        schemaPattern = translateSchema(schemaPattern);

        sb.append("where 1=1 ").append(
            and("pc.procedure_cat", "=", catalog)).append(
            and("pc.procedure_schem", "LIKE", schemaPattern)).append(
            and("pc.procedure_name", "LIKE", functionNamePattern)).append(
            and("pc.column_name", "LIKE", columnNamePattern)).append(
            " order by 1, 2, 3, 17, 18 , 15");

        // Order by FUNCTION_CAT, FUNCTION_SCHEM, FUNCTION_NAME, SPECIFIC_NAME
        //      COLUMN_GROUP and ORDINAL_POSITION
        return execute(sb.toString());
    }
