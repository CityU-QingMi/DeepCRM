    public static String escape(final String name) {
        final StringBuilder sb = new StringBuilder(name.length() * 2);
        boolean needsQuotes = false;
        for (int i = 0; i < name.length(); i++) {
            final char c = name.charAt(i);
            switch (c) {
            case '\\':
            case '*':
            case '?':
            case '\"':
                // quote, star, question & backslash must be escaped
                sb.append('\\');
                needsQuotes = true; // ... and can only appear in quoted value
                break;
            case ',':
            case '=':
            case ':':
                // no need to escape these, but value must be quoted
                needsQuotes = true;
                break;
            case '\r':
                // drop \r characters: \\r gives "invalid escape sequence"
                continue;
            case '\n':
                // replace \n characters with \\n sequence
                sb.append("\\n");
                needsQuotes = true;
                continue;
            }
            sb.append(c);
        }
        if (needsQuotes) {
            sb.insert(0, '\"');
            sb.append('\"');
        }
        return sb.toString();
    }
