    public int read() throws IOException {

        try {
            if (seekPosition >= fileLength) {
                return -1;
            }

            if (seekPosition < bufferOffset
                    || seekPosition >= bufferOffset + buffer.length) {
                readIntoBuffer();
            } else {
                cacheHit++;
            }

            int val = buffer[(int) (seekPosition - bufferOffset)] & 0xff;

            seekPosition++;

            return val;
        } catch (IOException e) {
            resetPointer();
            logger.logWarningEvent("read failed", e);

            throw e;
        }
    }
