    private static void flushRemainingBytes(final CharsetEncoder charsetEncoder,
            final ByteBufferDestination destination, ByteBuffer temp) {
        CoderResult result;
        do {
            // write any final bytes to the output buffer once the overall input sequence has been read
            result = charsetEncoder.flush(temp);
            temp = drainIfByteBufferFull(destination, temp, result);
        } while (result.isOverflow()); // byte buffer has been drained: retry
        if (!result.isUnderflow()) { // we should have fully flushed the remaining bytes
            throwException(result);
        }
        if (temp.remaining() > 0 && temp != destination.getByteBuffer()) {
            temp.flip();
            ByteBufferDestinationHelper.writeToUnsynchronized(temp, destination);
            temp.clear();
        }
    }
