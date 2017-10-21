    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        switch (type) {

            case StatementTypes.SIGNAL :
                sb.append(Tokens.T_SIGNAL).append(' ');
                sb.append(Tokens.T_SQLSTATE);
                sb.append(' ').append('\'').append(sqlState).append('\'');
                break;

            case StatementTypes.RESIGNAL :
                sb.append(Tokens.T_RESIGNAL).append(' ');
                sb.append(Tokens.T_SQLSTATE);
                sb.append(' ').append('\'').append(sqlState).append('\'');
                break;
        }

        return sb.toString();
    }
