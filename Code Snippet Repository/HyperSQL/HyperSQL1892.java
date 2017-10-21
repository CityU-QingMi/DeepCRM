    public void seek(long newPos) throws IOException {

        try {
            positionBufferSeek(newPos);
            buffer.position((int) (newPos - bufferPosition));
        } catch (IllegalArgumentException e) {
            logger.logWarningEvent(JVM_ERROR, e);

            IOException io = JavaSystem.toIOException(e);

            throw io;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
