    public long readLong() throws IOException {

        try {
            long value = buffer.getLong();

            positionBufferMove(8);

            return value;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
