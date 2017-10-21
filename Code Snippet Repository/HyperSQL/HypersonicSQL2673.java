    public void write(byte[] b, int off, int length) throws IOException {

        try {
            file.seek(seekPosition);

            if (seekPosition < bufferOffset + buffer.length
                    && seekPosition + length > bufferOffset) {
                writeToBuffer(b, off, length);
            }

            file.write(b, off, length);

            seekPosition += length;

            if (!extendLength && fileLength < seekPosition) {
                fileLength = seekPosition;
            }
        } catch (IOException e) {
            resetPointer();
            logger.logWarningEvent("failed to write a byte array", e);

            throw e;
        }
    }
