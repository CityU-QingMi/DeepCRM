    private static void writeEncodedText(final CharsetEncoder charsetEncoder, final CharBuffer charBuf,
            final ByteBuffer byteBuf, final ByteBufferDestination destination, CoderResult result) {
        if (!result.isUnderflow()) {
            writeChunkedEncodedText(charsetEncoder, charBuf, destination, byteBuf, result);
            return;
        }
        result = charsetEncoder.flush(byteBuf);
        if (!result.isUnderflow()) {
            synchronized (destination) {
                flushRemainingBytes(charsetEncoder, destination, byteBuf);
            }
            return;
        }
        // Thread-safety note: no explicit synchronization on ByteBufferDestination below. This is safe, because
        // if the byteBuf is actually the destination's buffer, this method call should be protected with
        // synchronization on the destination object at some level, so the call to destination.getByteBuffer() should
        // be safe. If the byteBuf is an unrelated buffer, the comparison between the buffers should fail despite
        // destination.getByteBuffer() is not protected with the synchronization on the destination object.
        if (byteBuf != destination.getByteBuffer()) {
            byteBuf.flip();
            destination.writeBytes(byteBuf);
            byteBuf.clear();
        }
    }
