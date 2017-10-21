    public void read(byte[] b, int offset, int length) throws IOException {

        try {
            if (seekPosition + length > fileLength) {
                throw new EOFException();
            }

            if (length > buffer.length
                    && (seekPosition < bufferOffset
                        || seekPosition >= bufferOffset + buffer.length)) {
                file.seek(seekPosition);
                file.readFully(b, offset, length);

                seekPosition += length;

                return;
            }

            if (seekPosition < bufferOffset
                    || seekPosition >= bufferOffset + buffer.length) {
                readIntoBuffer();
            } else {
                cacheHit++;
            }

            ba.reset();

            if (seekPosition - bufferOffset
                    != ba.skip(seekPosition - bufferOffset)) {
                throw new EOFException();
            }

            int bytesRead = ba.read(b, offset, length);

            seekPosition += bytesRead;

            if (bytesRead < length) {
                file.seek(seekPosition);
                file.readFully(b, offset + bytesRead, length - bytesRead);

                seekPosition += (length - bytesRead);
            }
        } catch (IOException e) {
            resetPointer();
            logger.logWarningEvent("failed to read a byte array", e);

            throw e;
        }
    }
