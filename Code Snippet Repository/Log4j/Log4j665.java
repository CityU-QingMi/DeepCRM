    private synchronized void remap() {
        final long offset = this.mappingOffset + mappedBuffer.position();
        final int length = mappedBuffer.remaining() + regionLength;
        try {
            unsafeUnmap(mappedBuffer);
            final long fileLength = randomAccessFile.length() + regionLength;
            LOGGER.debug("{} {} extending {} by {} bytes to {}", getClass().getSimpleName(), getName(), getFileName(),
                    regionLength, fileLength);

            final long startNanos = System.nanoTime();
            randomAccessFile.setLength(fileLength);
            final float millis = (float) ((System.nanoTime() - startNanos) / NANOS_PER_MILLISEC);
            LOGGER.debug("{} {} extended {} OK in {} millis", getClass().getSimpleName(), getName(), getFileName(),
                    millis);

            mappedBuffer = mmap(randomAccessFile.getChannel(), getFileName(), offset, length);
            this.byteBuffer = mappedBuffer;
            mappingOffset = offset;
        } catch (final Exception ex) {
            logError("Unable to remap", ex);
        }
    }
