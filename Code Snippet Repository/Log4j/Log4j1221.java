    public static void writeToUnsynchronized(final byte[] data, int offset, int length,
            final ByteBufferDestination destination) {
        ByteBuffer buffer = destination.getByteBuffer();
        while (length > buffer.remaining()) {
            final int chunk = buffer.remaining();
            buffer.put(data, offset, chunk);
            offset += chunk;
            length -= chunk;
            buffer = destination.drain(buffer);
        }
        buffer.put(data, offset, length);
        // No drain in the end.
    }
