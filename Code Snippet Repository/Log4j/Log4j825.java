    private static long parseNumber(final CharSequence text, final String parsed, final int multiplier,
            final String errorText) {
        // regex limits to [0-9]+
        if (parsed == null) {
            return 0;
        }
        try {
            final long val = Long.parseLong(parsed);
            return val * multiplier;
        } catch (final Exception ex) {
            throw new IllegalArgumentException("Text cannot be parsed to a Duration: " + errorText + " (in " + text
                    + ")", ex);
        }
    }
