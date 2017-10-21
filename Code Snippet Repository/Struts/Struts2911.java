    private void parseTemplateText(Node parent) throws JasperException {

        if (!reader.hasMoreInput())
            return;

        CharArrayWriter ttext = new CharArrayWriter();
        // Output the first character
        int ch = reader.nextChar();
        if (ch == '\\') {
            reader.pushChar();
        } else {
            ttext.write(ch);
        }

        while (reader.hasMoreInput()) {
            ch = reader.nextChar();
            if (ch == '<') {
                reader.pushChar();
                break;
            } else if ((ch == '$' || ch == '#') && !pageInfo.isELIgnored()) {
                if (!reader.hasMoreInput()) {
                    ttext.write(ch);
                    break;
                }
                if (reader.nextChar() == '{') {
                    reader.pushChar();
                    reader.pushChar();
                    break;
                }
                ttext.write(ch);
                reader.pushChar();
                continue;
            } else if (ch == '\\') {
                if (!reader.hasMoreInput()) {
                    ttext.write('\\');
                    break;
                }
                char next = (char) reader.peekChar();
                // Looking for \% or \$ or \#
                if (next == '%' || ((next == '$' || next == '#') &&
                        !pageInfo.isELIgnored())) {
                    ch = reader.nextChar();
                }
            }
            ttext.write(ch);
        }
        new Node.TemplateText(ttext.toString(), start, parent);
    }
