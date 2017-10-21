    public static String getKeyword(int token) {

        String key = (String) reservedKeys.getKey(token);

        if (key != null) {
            return key;
        }

        key = (String) commandSet.getKey(token);

        return key;
    }
