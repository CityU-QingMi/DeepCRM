    String parseToken(boolean quoted) throws JasperException {
        StringBuffer stringBuffer = new StringBuffer();
        skipSpaces();
        stringBuffer.setLength(0);
        
        if (!hasMoreInput()) {
            return "";
        }

        int ch = peekChar();
        
        if (quoted) {
            if (ch == '"' || ch == '\'') {

                char endQuote = ch == '"' ? '"' : '\'';
                // Consume the open quote: 
                ch = nextChar();
                for (ch = nextChar(); ch != -1 && ch != endQuote;
                         ch = nextChar()) {
                    if (ch == '\\') 
                        ch = nextChar();
                    stringBuffer.append((char) ch);
                }
                // Check end of quote, skip closing quote:
                if (ch == -1) {
                    err.jspError(mark(), "jsp.error.quotes.unterminated");
                }
            } else {
                err.jspError(mark(), "jsp.error.attr.quoted");
            }
        } else {
            if (!isDelimiter()) {
                // Read value until delimiter is found:
                do {
                    ch = nextChar();
                    // Take care of the quoting here.
                    if (ch == '\\') {
                        if (peekChar() == '"' || peekChar() == '\'' ||
                               peekChar() == '>' || peekChar() == '%')
                            ch = nextChar();
                    }
                    stringBuffer.append((char) ch);
                } while (!isDelimiter());
            }
        }

        return stringBuffer.toString();
    }
