    protected static Charset charsetForName(final String charsetName)
    throws SQLException {

        String csn = charsetName;

        if (csn == null) {
            csn = Charset.defaultCharset().name();
        }

        try {
            if (Charset.isSupported(csn)) {
                return Charset.forName(csn);
            }
        } catch (IllegalCharsetNameException x) {}

        throw JDBCUtil.sqlException(new UnsupportedEncodingException(csn));
    }
