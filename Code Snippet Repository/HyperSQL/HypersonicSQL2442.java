    protected int copyShadow(CachedObject row) {

        if (shadowFile != null) {
            long seekpos = row.getPos() * dataFileScale;

            try {
                int pageCount = shadowFile.copy(seekpos, row.getStorageSize());

                shadowFile.synch();

                return pageCount;
            } catch (Throwable t) {
                logSevereEvent("DataFileCache.copyShadow", t, row.getPos());

                throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
            }
        }

        return 0;
    }
