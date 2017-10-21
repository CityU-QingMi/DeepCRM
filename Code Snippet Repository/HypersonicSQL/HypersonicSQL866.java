    String getStartTransactionSQL() {

        StringBuffer sb = new StringBuffer();

        sb.append(Tokens.T_START).append(' ').append(Tokens.T_TRANSACTION);

        if (isolationLevel != isolationLevelDefault) {
            sb.append(' ');
            appendIsolationSQL(sb, isolationLevel);
        }

        return sb.toString();
    }
