    protected Boolean readBoole() {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        return s.equalsIgnoreCase(Tokens.T_TRUE) ? Boolean.TRUE
                                                 : Boolean.FALSE;
    }
