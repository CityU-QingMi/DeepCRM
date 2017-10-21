    private static ByteBuffer encodeAsMuchAsPossible(final CharsetEncoder charsetEncoder, final CharBuffer charBuf,
            final boolean endOfInput, final ByteBufferDestination destination, ByteBuffer temp) {
        CoderResult result;
        do {
            result = charsetEncoder.encode(charBuf, temp, endOfInput);
            temp = drainIfByteBufferFull(destination, temp, result);
        } while (result.isOverflow()); // byte buffer has been drained: retry
        if (!result.isUnderflow()) { // we should have fully read the char buffer contents
            throwException(result);
        }
        return temp;
    }
