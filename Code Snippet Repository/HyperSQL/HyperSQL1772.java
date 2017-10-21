    public void setBlockBytes(byte[] dataBytes, long position, int offset,
                              int length) {

        if (length == 0) {
            return;
        }

        if (file == null) {
            openFile();
        }

        try {
            file.seek(position);
            file.write(dataBytes, offset, length);
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
