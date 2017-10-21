    public void writeLong(long i) throws IOException {

        try {
            buffersModified = true;

            buffer.putLong(i);
            positionBufferMove(8);
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
