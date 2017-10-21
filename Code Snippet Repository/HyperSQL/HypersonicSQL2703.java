    public int read() throws IOException {

        try {
            int value = buffer.get();

            positionBufferMove(1);

            return value;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
