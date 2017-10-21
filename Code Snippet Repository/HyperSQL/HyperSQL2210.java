    protected Integer readSmallint() {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        try {
            int val = Integer.parseInt(s);

            return ValuePool.getInt(val);
        } catch (NumberFormatException e) {
            throw Error.error(e, ErrorCode.X_22501, s);
        }
    }
