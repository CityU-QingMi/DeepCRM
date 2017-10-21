    public void writeInt(int i) throws IOException {

        try {
            buffersModified = true;

            buffer.putInt(i);
            positionBufferMove(4);
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
