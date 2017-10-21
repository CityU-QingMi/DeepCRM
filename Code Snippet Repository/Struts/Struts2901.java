    private void parseELExpression(Node parent, char type) throws JasperException {
        start = reader.mark();
        Mark last = null;
        boolean singleQuoted = false, doubleQuoted = false;
        int currentChar;
        do {
            // XXX could move this logic to JspReader
            last = reader.mark(); // XXX somewhat wasteful
            currentChar = reader.nextChar();
            if (currentChar == '\\' && (singleQuoted || doubleQuoted)) {
                // skip character following '\' within quotes
                reader.nextChar();
                currentChar = reader.nextChar();
            }
            if (currentChar == -1)
                err.jspError(start, "jsp.error.unterminated", type + "{");
            if (currentChar == '"' && !singleQuoted)
                doubleQuoted = !doubleQuoted;
            if (currentChar == '\'' && !doubleQuoted)
                singleQuoted = !singleQuoted;
        } while (currentChar != '}' || (singleQuoted || doubleQuoted));

        new Node.ELExpression(type, reader.getText(start, last), start, parent);
    }
