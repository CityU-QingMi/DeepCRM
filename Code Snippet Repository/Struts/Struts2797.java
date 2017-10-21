    private boolean isDelimiter() throws JasperException {
        if (! isSpace()) {
            int ch = peekChar();
            // Look for a single-char work delimiter:
            if (ch == '=' || ch == '>' || ch == '"' || ch == '\''
                    || ch == '/') {
                return true;
            }
            // Look for an end-of-comment or end-of-tag:                
            if (ch == '-') {
                Mark mark = mark();
                if (((ch = nextChar()) == '>')
                        || ((ch == '-') && (nextChar() == '>'))) {
                    reset(mark);
                    return true;
                } else {
                    reset(mark);
                    return false;
                }
            }
            return false;
        } else {
            return true;
        }
    }
