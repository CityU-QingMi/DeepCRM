    public static FixedDateFormat createIfSupported(final String... options) {
        if (options == null || options.length == 0 || options[0] == null) {
            return new FixedDateFormat(FixedFormat.DEFAULT, TimeZone.getDefault());
        }
        final TimeZone tz;
        if (options.length > 1) {
            if (options[1] != null){
                tz = TimeZone.getTimeZone(options[1]);
            } else {
                tz = TimeZone.getDefault();
            }
        } else if (options.length > 2) {
            return null;
        } else {
            tz = TimeZone.getDefault();
        }

        final FixedFormat type = FixedFormat.lookup(options[0]);
        return type == null ? null : new FixedDateFormat(type, tz);
    }
