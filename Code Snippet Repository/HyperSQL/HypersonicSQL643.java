    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_CREATE).append(' ').append(Tokens.T_SYNONYM);
        sb.append(' ').append(name.getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_FOR).append(' ');
        sb.append(target.getSchemaQualifiedStatementName());

        return sb.toString();
    }
