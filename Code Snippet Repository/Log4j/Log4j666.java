    @Override
    public synchronized boolean closeOutputStream() {
        final long position = mappedBuffer.position();
        final long length = mappingOffset + position;
        try {
            unsafeUnmap(mappedBuffer);
        } catch (final Exception ex) {
            logError("Unable to unmap MappedBuffer", ex);
        }
        try {
            LOGGER.debug("MMapAppender closing. Setting {} length to {} (offset {} + position {})", getFileName(),
                    length, mappingOffset, position);
            randomAccessFile.setLength(length);
            randomAccessFile.close();
            return true;
        } catch (final IOException ex) {
            logError("Unable to close MemoryMappedFile", ex);
            return false;
        }
    }
