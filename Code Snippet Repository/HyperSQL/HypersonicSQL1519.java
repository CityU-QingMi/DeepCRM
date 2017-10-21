    protected static Charset charsetForName(final String charsetName)
            throws UnsupportedEncodingException {

        String csn = charsetName;

        if (csn == null) {
            csn = Charset.defaultCharset().name();
        }

        try {
            if (Charset.isSupported(csn)) {
                return Charset.forName(csn);
            }
        } catch (IllegalCharsetNameException x) {
            LOG.warning(x.getMessage(), x);
        }

        throw new UnsupportedEncodingException(csn);
    }
