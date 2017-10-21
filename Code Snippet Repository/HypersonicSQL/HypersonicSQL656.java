    public String getSQLAlter() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_ALTER).append(' ').append(Tokens.T_SPECIFIC);
        sb.append(' ').append(Tokens.T_ROUTINE).append(' ');
        sb.append(specificName.getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_BODY);
        sb.append(' ').append(statement.getSQL());

        return sb.toString();
    }
