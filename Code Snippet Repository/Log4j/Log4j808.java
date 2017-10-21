    private void writeHeader() {
        if (layout == null) {
            return;
        }
        final byte[] header = layout.getHeader();
        if (header == null) {
            return;
        }
        try {
            if (randomAccessFile.length() == 0) {
                // write to the file, not to the buffer: the buffer may not be empty
                randomAccessFile.write(header, 0, header.length);
            }
        } catch (final IOException e) {
            logError("Unable to write header", e);
        }
    }
