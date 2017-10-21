    protected int copyShadow(CachedObject[] rows, int offset, int count) {

        int pageCount = 0;

        if (shadowFile != null) {
            long time    = cache.shadowTimer.elapsedTime();
            long seekpos = 0;

            cache.shadowTimer.start();

            try {
                for (int i = offset; i < offset + count; i++) {
                    CachedObject row = rows[i];

                    seekpos = row.getPos() * dataFileScale;
                    pageCount += shadowFile.copy(seekpos,
                                                 row.getStorageSize());
                }

                if (pageCount > 0) {
                    shadowFile.synch();
                }
            } catch (Throwable t) {
                logSevereEvent("DataFileCache.copyShadow", t, seekpos);

                throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
            }

            cache.shadowTimer.stop();

            if (pageCount > 0) {
                time = cache.shadowTimer.elapsedTime() - time;

                logDetailEvent("copyShadow [size, time] "
                               + shadowFile.getSavedLength() + " " + time);
            }
        }

        return pageCount;
    }
