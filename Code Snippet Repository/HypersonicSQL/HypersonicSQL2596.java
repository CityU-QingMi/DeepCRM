    public void close() {

        try {
            if (file != null) {
                file.synch();
                file.close();
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
