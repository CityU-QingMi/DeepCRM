    public static long parse(final String string, final long defaultValue) {
        final Matcher matcher = VALUE_PATTERN.matcher(string);

        // Valid input?
        if (matcher.matches()) {
            try {
                // Get double precision value
                final long value = NumberFormat.getNumberInstance(Locale.getDefault()).parse(
                    matcher.group(1)).longValue();

                // Get units specified
                final String units = matcher.group(3);

                if (units.isEmpty()) {
                    return value;
                } else if (units.equalsIgnoreCase("K")) {
                    return value * KB;
                } else if (units.equalsIgnoreCase("M")) {
                    return value * MB;
                } else if (units.equalsIgnoreCase("G")) {
                    return value * GB;
                } else {
                    LOGGER.error("FileSize units not recognized: " + string);
                    return defaultValue;
                }
            } catch (final ParseException e) {
                LOGGER.error("FileSize unable to parse numeric part: " + string, e);
                return defaultValue;
            }
        }
        LOGGER.error("FileSize unable to parse bytes: " + string);
        return defaultValue;
    }
