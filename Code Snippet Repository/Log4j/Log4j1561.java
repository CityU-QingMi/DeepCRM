    public static String escapeHtmlTags(final String input) {
        // Check if the string is null, zero length or devoid of special characters
        // if so, return what was sent in.

        if (Strings.isEmpty(input)
            || (input.indexOf('"') == -1 &&
            input.indexOf('&') == -1 &&
            input.indexOf('<') == -1 &&
            input.indexOf('>') == -1)) {
            return input;
        }

        //Use a StringBuilder in lieu of String concatenation -- it is
        //much more efficient this way.

        final StringBuilder buf = new StringBuilder(input.length() + 6);

        final int len = input.length();
        for (int i = 0; i < len; i++) {
            final char ch = input.charAt(i);
            if (ch > '>') {
                buf.append(ch);
            } else if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else if (ch == '&') {
                buf.append("&amp;");
            } else if (ch == '"') {
                buf.append("&quot;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }
