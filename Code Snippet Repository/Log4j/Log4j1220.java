    public static void writeToUnsynchronized(final ByteBuffer source, final ByteBufferDestination destination) {
        ByteBuffer destBuff = destination.getByteBuffer();
        while (source.remaining() > destBuff.remaining()) {
            final int originalLimit = source.limit();
            source.limit(Math.min(source.limit(), source.position() + destBuff.remaining()));
            destBuff.put(source);
            source.limit(originalLimit);
            destBuff = destination.drain(destBuff);
        }
        destBuff.put(source);
        // No drain in the end.
    }
