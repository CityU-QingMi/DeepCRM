    private String parseQuoted(Mark start, String tx, char quote)
            throws JasperException {
        StringBuffer buf = new StringBuffer();
        int size = tx.length();
        int i = 0;
        while (i < size) {
            char ch = tx.charAt(i);
            if (ch == '&') {
                if (i + 5 < size && tx.charAt(i + 1) == 'a'
                        && tx.charAt(i + 2) == 'p' && tx.charAt(i + 3) == 'o'
                        && tx.charAt(i + 4) == 's' && tx.charAt(i + 5) == ';') {
                    buf.append('\'');
                    i += 6;
                } else if (i + 5 < size && tx.charAt(i + 1) == 'q'
                        && tx.charAt(i + 2) == 'u' && tx.charAt(i + 3) == 'o'
                        && tx.charAt(i + 4) == 't' && tx.charAt(i + 5) == ';') {
                    buf.append('"');
                    i += 6;
                } else {
                    buf.append(ch);
                    ++i;
                }
            } else if (ch == '\\' && i + 1 < size) {
                ch = tx.charAt(i + 1);
                if (ch == '\\' || ch == '\"' || ch == '\'' || ch == '>') {
                    // \ " and ' are always unescaped regardless of if they are
                    // inside or outside of an EL expression. JSP.1.6 takes
                    // precedence over JSP.1.3.10 (confirmed with EG).
                    buf.append(ch);
                    i += 2;
                } else {
                    buf.append('\\');
                    ++i;
                }
            } else if (ch == quote && STRICT_QUOTE_ESCAPING) {
                // Unescaped quote character
                err.jspError(start, "jsp.error.attribute.noescape", tx,
                        "" + quote);
            } else {
                buf.append(ch);
                ++i;
            }
        }
        return buf.toString();
    }
