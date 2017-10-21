    private static ByteBuffer writeAndEncodeAsMuchAsPossible(final CharsetEncoder charsetEncoder,
            final CharBuffer charBuf, final boolean endOfInput, final ByteBufferDestination destination,
            ByteBuffer temp, CoderResult result) {
        while (true) {
            temp = drainIfByteBufferFull(destination, temp, result);
            if (!result.isOverflow()) {
                break;
            }
            result = charsetEncoder.encode(charBuf, temp, endOfInput);
        }
        if (!result.isUnderflow()) { // we should have fully read the char buffer contents
            throwException(result);
        }
        return temp;
    }
