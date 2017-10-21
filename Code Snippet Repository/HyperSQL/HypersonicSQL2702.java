    public long getFilePointer() throws IOException {

        try {
            return currentPosition;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
