    protected void saveRows(CachedObject[] rows, int offset, int count) {

        if (count == 0) {
            return;
        }

        int  pageCount   = copyShadow(rows, offset, count);
        long startTime   = cache.saveAllTimer.elapsedTime();
        long storageSize = 0;

        cache.saveAllTimer.start();

        if (pageCount > 0) {
            setFileModified();
        }

        for (int i = offset; i < offset + count; i++) {
            CachedObject r = rows[i];

            saveRowNoLock(r);

            rows[i]     = null;
            storageSize += r.getStorageSize();
        }

        cache.saveAllTimer.stop();
        cache.logSaveRowsEvent(count, storageSize, startTime);
    }
