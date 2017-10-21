    private void scanPIData(String target, XMLString data)
            throws IOException, JasperException {

        // check target
        if (target.length() == 3) {
            char c0 = Character.toLowerCase(target.charAt(0));
            char c1 = Character.toLowerCase(target.charAt(1));
            char c2 = Character.toLowerCase(target.charAt(2));
            if (c0 == 'x' && c1 == 'm' && c2 == 'l') {
                err.jspError("jsp.error.xml.reservedPITarget");
            }
        }

        // spaces
        if (!skipSpaces()) {
            if (skipString("?>")) {
                // we found the end, there is no data
                data.clear();
                return;
            } else {
                // if there is data there should be some space
                err.jspError("jsp.error.xml.spaceRequiredInPI");
            }
        }

        fStringBuffer.clear();
        // data
        if (scanData("?>", fStringBuffer)) {
            do {
                int c = peekChar();
                if (c != -1) {
                    if (XMLChar.isHighSurrogate(c)) {
                        scanSurrogates(fStringBuffer);
                    } else if (XMLChar.isInvalid(c)) {
                        err.jspError("jsp.error.xml.invalidCharInPI",
                                Integer.toHexString(c));
                        scanChar();
                    }
                }
            } while (scanData("?>", fStringBuffer));
        }
        data.setValues(fStringBuffer);

    }
