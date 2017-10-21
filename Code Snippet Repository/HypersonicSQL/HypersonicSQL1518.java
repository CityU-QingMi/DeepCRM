    protected final void setEncoding(final String encoding)
            throws UnsupportedEncodingException {

        final Charset charSet = charsetForName(encoding);
        final CharsetEncoder encoder = charSet.newEncoder().onMalformedInput(
                CodingErrorAction.REPLACE).onUnmappableCharacter(
                        CodingErrorAction.REPLACE);
        final float maxBytesPerChar = encoder.maxBytesPerChar();
        final float averageBytesPerChar = encoder.averageBytesPerChar();
        final boolean fixedWidthCharset = (maxBytesPerChar == Math.round(
                maxBytesPerChar)) && (maxBytesPerChar == averageBytesPerChar);

        //
        m_fixedWidthCharset = fixedWidthCharset;
        m_maxCharWidth = Math.round(maxBytesPerChar);
        m_charset = charSet;
        m_encoder = encoder;
        m_encoding = m_charset.name();
    }
