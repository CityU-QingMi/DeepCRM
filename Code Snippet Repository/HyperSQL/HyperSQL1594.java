    protected void readObject(long pos, int size) {

        try {
            rowIn.resetBlock(pos, size);
            dataFile.seek(pos * dataFileScale);
            dataFile.read(rowIn.getBuffer(), 0, size);
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.readObject", t, pos);

            HsqlException ex = Error.error(ErrorCode.DATA_FILE_ERROR, t);

            ex.info = rowIn;

            throw ex;
        }
    }
