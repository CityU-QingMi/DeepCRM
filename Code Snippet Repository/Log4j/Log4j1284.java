    private static ByteBuffer drainIfByteBufferFull(final ByteBufferDestination destination, final ByteBuffer temp,
            final CoderResult result) {
        if (result.isOverflow()) { // byte buffer full
            // all callers already synchronize on destination but for safety ensure we are synchronized because
            // below calls to drain() may cause destination to swap in a new ByteBuffer object
            synchronized (destination) {
                final ByteBuffer destinationBuffer = destination.getByteBuffer();
                if (destinationBuffer != temp) {
                    temp.flip();
                    ByteBufferDestinationHelper.writeToUnsynchronized(temp, destination);
                    temp.clear();
                    return destination.getByteBuffer();
                } else {
                    return destination.drain(destinationBuffer);
                }
            }
        } else {
            return temp;
        }
    }
