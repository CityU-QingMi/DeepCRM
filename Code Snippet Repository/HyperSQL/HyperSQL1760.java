    public byte[] getBlockBytes(int blockAddress, int blockCount) {

        try {
            long   address   = (long) blockAddress * lobBlockSize;
            int    count     = blockCount * lobBlockSize;
            byte[] dataBytes = new byte[count];

            fileSeek(address);
            dataInput.readFully(dataBytes, 0, count);

            realPosition = address + count;

            return dataBytes;
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
