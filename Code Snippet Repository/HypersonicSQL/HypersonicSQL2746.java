    public CachedObject get(RowInputInterface in) {

        try {
            RowAVLDiskData row = new RowAVLDiskData(this, table, in);

            row.setPos(in.getFilePosition());
            row.setStorageSize(in.getSize());
            row.setChanged(false);
            ((TextCache) cache).addInit(row);

            return row;
        } catch (IOException e) {
            throw Error.error(ErrorCode.TEXT_FILE_IO, e);
        }
    }
