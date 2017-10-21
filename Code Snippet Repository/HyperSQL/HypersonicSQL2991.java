    protected Double readReal() {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            throw Error.error(e, ErrorCode.X_22501, s);
        }
    }
