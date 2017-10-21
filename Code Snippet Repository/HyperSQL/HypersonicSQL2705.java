    public int readInt() throws IOException {

        try {
            int value = buffer.getInt();

            positionBufferMove(4);

            return value;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
