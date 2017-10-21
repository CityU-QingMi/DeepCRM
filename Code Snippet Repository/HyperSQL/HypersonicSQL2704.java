    public void read(byte[] b, int offset, int length) throws IOException {

        try {
            while (true) {
                checkBuffer();

                long transferLength = bufferPosition + bufferLength
                                      - currentPosition;

                if (transferLength > length) {
                    transferLength = length;
                }

                buffer.get(b, offset, (int) transferLength);
                positionBufferMove((int) transferLength);

                length -= transferLength;
                offset += transferLength;

                if (length == 0) {
                    break;
                }
            }
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
