    private Token nextToken() {
        skipSpaces();
        if (hasNextChar()) {
            char ch = nextChar();
            if (Character.isJavaIdentifierStart(ch)) {
                StringBuffer buf = new StringBuffer();
                buf.append(ch);
                while ((ch = peekChar()) != -1
                        && Character.isJavaIdentifierPart(ch)) {
                    buf.append(ch);
                    nextChar();
                }
                return new Id(buf.toString());
            }

            if (ch == '\'' || ch == '"') {
                return parseQuotedChars(ch);
            } else {
                // For now...
                return new Char(ch);
            }
        }
        return null;
    }
