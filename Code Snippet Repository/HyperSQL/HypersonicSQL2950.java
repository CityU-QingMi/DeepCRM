    public String getConnectUserSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_SESSION).append(' ');
        sb.append(Tokens.T_AUTHORIZATION).append(' ');
        sb.append(StringConverter.toQuotedString(getName().getNameString(),
                '\'', true));

        return sb.toString();
    }
