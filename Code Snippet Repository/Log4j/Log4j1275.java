    @Override
    public void encode(final StringBuilder source, final ByteBufferDestination destination) {
        try {
            final Object[] threadLocalState = getThreadLocalState();
            final CharsetEncoder charsetEncoder = (CharsetEncoder) threadLocalState[0];
            final CharBuffer charBuffer = (CharBuffer) threadLocalState[1];
            final ByteBuffer byteBuffer = (ByteBuffer) threadLocalState[2];
            TextEncoderHelper.encodeText(charsetEncoder, charBuffer, byteBuffer, source, destination);
        } catch (final Exception ex) {
            logEncodeTextException(ex, source, destination);
            TextEncoderHelper.encodeTextFallBack(charset, source, destination);
        }
    }
