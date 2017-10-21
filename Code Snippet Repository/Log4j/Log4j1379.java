    private static Formatter createNonFixedFormatter(final String[] options) {
        // if we get here, options is a non-null array with at least one element (first of which non-null)
        Objects.requireNonNull(options);
        if (options.length == 0) {
            throw new IllegalArgumentException("options array must have at least one element");
        }
        Objects.requireNonNull(options[0]);
        final String patternOption = options[0];
        if (UNIX_FORMAT.equals(patternOption)) {
            return new UnixFormatter();
        }
        if (UNIX_MILLIS_FORMAT.equals(patternOption)) {
            return new UnixMillisFormatter();
        }
        // LOG4J2-1149: patternOption may be a name (if a time zone was specified)
        final FixedDateFormat.FixedFormat fixedFormat = FixedDateFormat.FixedFormat.lookup(patternOption);
        final String pattern = fixedFormat == null ? patternOption : fixedFormat.getPattern();

        // if the option list contains a TZ option, then set it.
        TimeZone tz = null;
        if (options.length > 1 && options[1] != null) {
            tz = TimeZone.getTimeZone(options[1]);
        }

        try {
            final FastDateFormat tempFormat = FastDateFormat.getInstance(pattern, tz);
            return new PatternFormatter(tempFormat);
        } catch (final IllegalArgumentException e) {
            LOGGER.warn("Could not instantiate FastDateFormat with pattern " + pattern, e);

            // default to the DEFAULT format
            return createFixedFormatter(FixedDateFormat.create(FixedFormat.DEFAULT, tz));
        }
    }
