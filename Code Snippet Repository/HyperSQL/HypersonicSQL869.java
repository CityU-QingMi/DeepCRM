    static String getIsolationString(int isolationLevel) {

        switch (isolationLevel) {

            case SessionInterface.TX_READ_UNCOMMITTED :
            case SessionInterface.TX_READ_COMMITTED :
                StringBuffer sb = new StringBuffer();

                sb.append(Tokens.T_READ).append(' ');
                sb.append(Tokens.T_COMMITTED);

                return sb.toString();

            case SessionInterface.TX_REPEATABLE_READ :
            case SessionInterface.TX_SERIALIZABLE :
            default :
                return Tokens.T_SERIALIZABLE;
        }
    }
