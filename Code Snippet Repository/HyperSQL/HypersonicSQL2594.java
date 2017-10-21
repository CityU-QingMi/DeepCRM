    public long getLength() {

        if (file == null) {
            openFile();
        }

        try {
            return file.length();
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
