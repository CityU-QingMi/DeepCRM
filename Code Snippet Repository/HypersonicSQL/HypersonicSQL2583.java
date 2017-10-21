    public void close() {

        try {
            if (dataInput != null) {
                dataInput.close();
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
