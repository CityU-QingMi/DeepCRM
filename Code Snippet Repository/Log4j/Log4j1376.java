    public static String createSequence(final String... names) {
        if (names == null) {
            return getDefaultStyle();
        }
        final StringBuilder sb = new StringBuilder(AnsiEscape.CSI.getCode());
        boolean first = true;
        for (final String name : names) {
            try {
                final AnsiEscape escape = EnglishEnums.valueOf(AnsiEscape.class, name.trim());
                if (!first) {
                    sb.append(AnsiEscape.SEPARATOR.getCode());
                }
                first = false;
                sb.append(escape.getCode());
            } catch (final Exception ex) {
                // Ignore the error.
            }
        }
        sb.append(AnsiEscape.SUFFIX.getCode());
        return sb.toString();
    }
