    static void encodeText(final CharsetEncoder charsetEncoder, final CharBuffer charBuf, final ByteBuffer byteBuf,
            final StringBuilder text, final ByteBufferDestination destination)
            throws CharacterCodingException {
        charsetEncoder.reset();
        if (text.length() > charBuf.capacity()) {
            encodeChunkedText(charsetEncoder, charBuf, byteBuf, text, destination);
            return;
        }
        charBuf.clear();
        text.getChars(0, text.length(), charBuf.array(), charBuf.arrayOffset());
        charBuf.limit(text.length());
        final CoderResult result = charsetEncoder.encode(charBuf, byteBuf, true);
        writeEncodedText(charsetEncoder, charBuf, byteBuf, destination, result);
    }
