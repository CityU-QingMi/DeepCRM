    public static long toFileSize(final String value, final long defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        String str = value.trim().toUpperCase(Locale.ENGLISH);
        long multiplier = 1;
        int index;

        if ((index = str.indexOf("KB")) != -1) {
            multiplier = ONE_K;
            str = str.substring(0, index);
        } else if ((index = str.indexOf("MB")) != -1) {
            multiplier = ONE_K * ONE_K;
            str = str.substring(0, index);
        } else if ((index = str.indexOf("GB")) != -1) {
            multiplier = ONE_K * ONE_K * ONE_K;
            str = str.substring(0, index);
        }
        try {
            return Long.parseLong(str) * multiplier;
        } catch (final NumberFormatException e) {
            LOGGER.error("[{}] is not in proper int form.", str);
            LOGGER.error("[{}] not in expected format.", value, e);
        }
        return defaultValue;
    }
