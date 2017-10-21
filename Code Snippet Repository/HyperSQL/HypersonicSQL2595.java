    public void setLength(long length) {

        try {
            if (file != null) {
                file.setLength(length);
                file.synch();
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
