    public void close() throws IOException {

        try {
            logger.logDetailEvent("NIO file close, size: " + fileLength);

            buffer  = null;
            channel = null;

            for (int i = 0; i < buffers.length; i++) {
                unmap(buffers[i]);

                buffers[i] = null;
            }

            file.close();

            // System.gc();
        } catch (Throwable t) {
            logger.logWarningEvent("NIO buffer close error", t);

            IOException io = JavaSystem.toIOException(t);

            throw io;
        }
    }
