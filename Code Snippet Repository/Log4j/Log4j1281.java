    @Deprecated
    public static void encodeText(final CharsetEncoder charsetEncoder, final CharBuffer charBuf,
            final ByteBufferDestination destination) {
        charsetEncoder.reset();
        synchronized (destination) {
            ByteBuffer byteBuf = destination.getByteBuffer();
            byteBuf = encodeAsMuchAsPossible(charsetEncoder, charBuf, true, destination, byteBuf);
            flushRemainingBytes(charsetEncoder, destination, byteBuf);
        }
    }
