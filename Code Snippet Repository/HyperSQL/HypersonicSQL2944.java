    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_CREATE).append(' ').append(Tokens.T_USER);
        sb.append(' ').append(granteeName.statementName).append(' ');
        sb.append(Tokens.T_PASSWORD).append(' ').append(Tokens.T_DIGEST);
        sb.append(' ').append('\'').append(password).append('\'');

        return sb.toString();
    }
