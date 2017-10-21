    static String getSetSchemaSQL(HsqlName schemaName) {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_SCHEMA).append(' ');
        sb.append(schemaName.statementName);

        return sb.toString();
    }
