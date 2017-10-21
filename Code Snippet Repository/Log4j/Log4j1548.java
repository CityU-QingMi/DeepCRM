    public static int toInt(final String value, final int defaultValue) {
        if (value != null) {
            final String s = value.trim();
            try {
                return Integer.parseInt(s);
            } catch (final NumberFormatException e) {
                LOGGER.error("[{}] is not in proper int form.", s, e);
            }
        }
        return defaultValue;
    }
