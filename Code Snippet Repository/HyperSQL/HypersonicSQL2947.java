    public String getLocalUserSQL() {

        StringBuffer sb = new StringBuffer(64);

        sb.append(Tokens.T_ALTER).append(' ');
        sb.append(Tokens.T_USER).append(' ');
        sb.append(getName().getStatementName()).append(' ');
        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_LOCAL);
        sb.append(' ').append(Tokens.T_TRUE);

        return sb.toString();
    }
