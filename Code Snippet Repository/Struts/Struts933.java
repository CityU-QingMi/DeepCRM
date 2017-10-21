    public static String URLDecode(byte[] bytes, String enc, boolean isQuery) {

        if (bytes == null)
            return null;

        int len = bytes.length;
        int ix = 0;
        int ox = 0;
        while (ix < len) {
            byte b = bytes[ix++];     // Get byte to test
            if (b == '+' && isQuery) {
                b = (byte)' ';
            } else if (b == '%') {
                if (ix + 2 > len) {
                    throw new IllegalArgumentException(
                            "The % character must be followed by two hexademical digits");
                }
                b = (byte) ((convertHexDigit(bytes[ix++]) << 4)
                            + convertHexDigit(bytes[ix++]));
            }
            bytes[ox++] = b;
        }
        if (enc != null) {
            try {
                return new String(bytes, 0, ox, B2CConverter.getCharset(enc));
            } catch (UnsupportedEncodingException uee) {
                if (log.isDebugEnabled()) {
                    log.debug("Unable to URL decode the specified input since the encoding " + enc + " is not supported.", uee);
                }
                return null;
            }
        }
        return new String(bytes, 0, ox);

    }
