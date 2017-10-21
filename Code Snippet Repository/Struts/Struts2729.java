    private String skipUntilEL() {
        char prev = 0;
        StringBuffer buf = new StringBuffer();
        while (hasNextChar()) {
            char ch = nextChar();
            if (prev == '\\') {
                prev = 0;
                if (ch == '\\') {
                    buf.append('\\');
                    if (!escapeBS)
                        prev = '\\';
                } else if (ch == '$' || ch == '#') {
                    buf.append(ch);
                }
                // else error!
            } else if (prev == '$' || prev == '#') {
                if (ch == '{') {
                    this.type = prev;
                    prev = 0;
                    break;
                }
                buf.append(prev);
                prev = 0;
            }
            if (ch == '\\' || ch == '$' || ch == '#') {
                prev = ch;
            } else {
                buf.append(ch);
            }
        }
        if (prev != 0) {
            buf.append(prev);
        }
        return buf.toString();
    }
