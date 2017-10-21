    private static long parseTimestamp(final String timestamp, final long defaultValue) {
        if (timestamp == null) {
            return defaultValue;
        }
        final SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        stf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return stf.parse(timestamp).getTime();
        } catch (final ParseException e) {
            LOGGER.warn("Error parsing TimeFilter timestamp value {}", timestamp, e);
            return defaultValue;
        }
    }
