    private static void encodeChunkedText(final CharsetEncoder charsetEncoder, final CharBuffer charBuf,
            ByteBuffer byteBuf, final StringBuilder text, final ByteBufferDestination destination) {

        // LOG4J2-1874 ByteBuffer, CharBuffer and CharsetEncoder are thread-local, so no need to synchronize while
        // modifying these objects. Postpone synchronization until accessing the ByteBufferDestination.
        int start = 0;
        CoderResult result = CoderResult.UNDERFLOW;
        boolean endOfInput = false;
        while (!endOfInput && result.isUnderflow()) {
            charBuf.clear();
            final int copied = copy(text, start, charBuf);
            start += copied;
            endOfInput = start >= text.length();
            charBuf.flip();
            result = charsetEncoder.encode(charBuf, byteBuf, endOfInput);
        }
        if (endOfInput) {
            writeEncodedText(charsetEncoder, charBuf, byteBuf, destination, result);
            return;
        }
        synchronized (destination) {
            byteBuf = writeAndEncodeAsMuchAsPossible(charsetEncoder, charBuf, endOfInput, destination, byteBuf,
                    result);
            while (!endOfInput) {
                result = CoderResult.UNDERFLOW;
                while (!endOfInput && result.isUnderflow()) {
                    charBuf.clear();
                    final int copied = copy(text, start, charBuf);
                    start += copied;
                    endOfInput = start >= text.length();
                    charBuf.flip();
                    result = charsetEncoder.encode(charBuf, byteBuf, endOfInput);
                }
                byteBuf = writeAndEncodeAsMuchAsPossible(charsetEncoder, charBuf, endOfInput, destination, byteBuf,
                        result);
            }
            flushRemainingBytes(charsetEncoder, destination, byteBuf);
        }
    }
