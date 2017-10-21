    @Override
    protected synchronized void write(final byte[] bytes, int offset, int length, final boolean immediateFlush) {
        while (length > mappedBuffer.remaining()) {
            final int chunk = mappedBuffer.remaining();
            mappedBuffer.put(bytes, offset, chunk);
            offset += chunk;
            length -= chunk;
            remap();
        }
        mappedBuffer.put(bytes, offset, length);

        // no need to call flush() if force is true,
        // already done in AbstractOutputStreamAppender.append
    }
