    public BinaryData(long length, DataInput stream) {

        data      = new byte[(int) length];
        bitLength = data.length * 8L;

        try {
            stream.readFully(data);
        } catch (IOException e) {
            throw Error.error(ErrorCode.GENERAL_IO_ERROR, e);
        }
    }
