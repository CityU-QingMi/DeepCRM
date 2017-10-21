    String getSessionIsolationSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_SET).append(' ').append(Tokens.T_SESSION);
        sb.append(' ').append(Tokens.T_CHARACTERISTICS).append(' ');
        sb.append(Tokens.T_AS).append(' ').append(Tokens.T_TRANSACTION).append(
            ' ');
        appendIsolationSQL(sb, isolationLevelDefault);

        return sb.toString();
    }
