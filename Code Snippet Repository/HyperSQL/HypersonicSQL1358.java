    public static String getMessage(final int code, int subCode,
                                    final Object[] add) {

        String message = getResourceString(code);

        if (subCode != 0) {
            message += getResourceString(subCode);
        }

        if (add != null) {
            message = insertStrings(message, add);
        }

        return message;
    }
