    static String camelCase(final String customLevel) {
        final StringBuilder sb = new StringBuilder(customLevel.length());
        boolean lower = true;
        for (final char ch : customLevel.toCharArray()) {
            if (ch == '_') {
                lower = false;
                continue;
            }
            sb.append(lower ? Character.toLowerCase(ch) : Character.toUpperCase(ch));
            lower = true;
        }
        return sb.toString();
    }
