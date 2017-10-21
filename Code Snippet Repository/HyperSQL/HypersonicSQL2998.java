    protected Boolean readBoole() {

        readFieldPrefix();
        scanner.scanNext();

        String token = scanner.getString();

        value = null;

        if (token.equalsIgnoreCase(Tokens.T_TRUE)) {
            value = Boolean.TRUE;
        } else if (token.equalsIgnoreCase(Tokens.T_FALSE)) {
            value = Boolean.FALSE;
        }

        return (Boolean) value;
    }
