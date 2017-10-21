    public String getInitialSchemaSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_ALTER).append(' ');
        sb.append(Tokens.T_USER).append(' ');
        sb.append(getName().getStatementName()).append(' ');
        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_INITIAL).append(' ');
        sb.append(Tokens.T_SCHEMA).append(' ');
        sb.append(initialSchema.getStatementName());

        return sb.toString();
    }
