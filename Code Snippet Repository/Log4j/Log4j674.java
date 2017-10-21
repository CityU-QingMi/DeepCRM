    protected synchronized void write(final byte[] bytes, final int offset, final int length, final boolean immediateFlush) {
        if (immediateFlush && byteBuffer.position() == 0) {
            writeToDestination(bytes, offset, length);
            flushDestination();
            return;
        }
        if (length >= byteBuffer.capacity()) {
            // if request length exceeds buffer capacity, flush the buffer and write the data directly
            flush();
            writeToDestination(bytes, offset, length);
        } else {
            if (length > byteBuffer.remaining()) {
                flush();
            }
            byteBuffer.put(bytes, offset, length);
        }
        if (immediateFlush) {
            flush();
        }
    }
