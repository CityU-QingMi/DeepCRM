    public void write(byte[] b, int offset, int length) throws IOException {

        long transferLength;

        try {
            buffersModified = true;

            while (true) {
                checkBuffer();

                transferLength = bufferPosition + bufferLength
                                 - currentPosition;

                if (transferLength > length) {
                    transferLength = length;
                }

                buffer.put(b, offset, (int) transferLength);
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
