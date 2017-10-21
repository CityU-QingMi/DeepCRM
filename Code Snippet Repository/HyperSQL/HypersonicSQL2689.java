    public long length() throws IOException {

        try {
            return file.length();
        } catch (IOException e) {
            logger.logWarningEvent(JVM_ERROR, e);

            throw e;
        } catch (Throwable t) {
            logger.logWarningEvent(JVM_ERROR, t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
