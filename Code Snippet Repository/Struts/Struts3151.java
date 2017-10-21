    private boolean scanSurrogates(XMLStringBuffer buf)
            throws IOException, JasperException {

        int high = scanChar();
        int low = peekChar();
        if (!XMLChar.isLowSurrogate(low)) {
            err.jspError("jsp.error.xml.invalidCharInContent",
                    Integer.toString(high, 16));
            return false;
        }
        scanChar();

        // convert surrogates to supplemental character
        int c = XMLChar.supplemental((char) high, (char) low);

        // supplemental character must be a valid XML character
        if (!XMLChar.isValid(c)) {
            err.jspError("jsp.error.xml.invalidCharInContent",
                    Integer.toString(c, 16));
            return false;
        }

        // fill in the buffer
        buf.append((char) high);
        buf.append((char) low);

        return true;

    }
