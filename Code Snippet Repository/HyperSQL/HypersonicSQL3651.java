    public String getDatabaseCollationSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_DATABASE).append(' ');
        sb.append(Tokens.T_COLLATION).append(' ');
        sb.append(getName().statementName);
        sb.append(' ');

        if (!padSpace) {
            sb.append(Tokens.T_NO).append(' ').append(Tokens.T_PAD);
        }

        return sb.toString();
    }
