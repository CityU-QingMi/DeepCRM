    public String getRestartSQL() {

        StringBuffer sb = new StringBuffer(128);

        sb.append(Tokens.T_ALTER).append(' ');
        sb.append(Tokens.T_SEQUENCE);
        sb.append(' ').append(name.getSchemaQualifiedStatementName());
        sb.append(' ').append(Tokens.T_RESTART);
        sb.append(' ').append(Tokens.T_WITH).append(' ').append(peek());

        return sb.toString();
    }
