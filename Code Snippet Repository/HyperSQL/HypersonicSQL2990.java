    protected Long readBigint() {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        try {
            long val = Long.parseLong(s);

            return ValuePool.getLong(val);
        } catch (NumberFormatException e) {
            throw Error.error(e, ErrorCode.X_22501, s);
        }
    }
