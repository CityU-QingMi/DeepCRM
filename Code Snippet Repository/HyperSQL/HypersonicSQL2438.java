    public void saveRowOutput(long pos) {

        try {
            dataFile.seek(pos * dataFileScale);
            dataFile.write(rowOut.getOutputStream().getBuffer(), 0,
                           rowOut.getOutputStream().size());
        } catch (Throwable t) {
            logSevereEvent("DataFileCache.saveRowOutput", t, pos);

            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
