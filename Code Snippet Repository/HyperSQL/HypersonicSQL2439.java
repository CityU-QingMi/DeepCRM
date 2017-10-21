    protected void saveRowNoLock(CachedObject row) {

        try {
            rowOut.reset();
            row.write(rowOut);
            dataFile.seek(row.getPos() * dataFileScale);
            dataFile.write(rowOut.getOutputStream().getBuffer(), 0,
                           rowOut.getOutputStream().size());
            row.setChanged(false);
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.saveRowNoLock", t, row.getPos());

            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
