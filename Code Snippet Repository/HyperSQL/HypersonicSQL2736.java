    public CachedObject get(RowInputInterface in) {

        try {
            if (largeData) {
                return new RowAVLDiskLarge(this, in);
            } else {
                return new RowAVLDisk(this, in);
            }
        } catch (IOException e) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, e);
        }
    }
