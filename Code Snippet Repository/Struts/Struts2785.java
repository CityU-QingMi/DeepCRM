    public void comment(char[] buf, int offset, int len) throws SAXException {

        processChars();  // Flush char buffer and remove white spaces

        // ignore comments in the DTD
        if (!inDTD) {
            startMark =
                new Mark(
                    ctxt,
                    path,
                    locator.getLineNumber(),
                    locator.getColumnNumber());
            new Node.Comment(new String(buf, offset, len), startMark, current);
        }
    }
