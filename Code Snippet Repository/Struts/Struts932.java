    public static String URLDecode(String str, String enc, boolean isQuery) {
        if (str == null)
            return (null);

        // use the specified encoding to extract bytes out of the
        // given string so that the encoding is not lost. If an
        // encoding is not specified, use ISO-8859-1
        byte[] bytes = null;
        try {
            if (enc == null) {
                bytes = str.getBytes("ISO-8859-1");
            } else {
                bytes = str.getBytes(B2CConverter.getCharset(enc));
            }
        } catch (UnsupportedEncodingException uee) {
            if (log.isDebugEnabled()) {
                log.debug("Unable to URL decode the specified input since the encoding "+ enc + " is not supported.", uee);
            }
        }

        return URLDecode(bytes, enc, isQuery);

    }
