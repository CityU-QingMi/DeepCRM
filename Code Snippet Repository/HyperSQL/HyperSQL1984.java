    public long getFilePosition(int rowSize, boolean asBlocks) {

        cache.writeLock.lock();

        try {
            long position;
            long newFreePosition;

            position        = cache.getFileFreePos() / scale;
            newFreePosition = cache.getFileFreePos() + rowSize;

            if (newFreePosition > cache.maxDataFileSize) {
                cache.logSevereEvent("data file reached maximum size "
                                     + cache.dataFileName, null);

                throw Error.error(ErrorCode.DATA_FILE_IS_FULL);
            }

            cache.fileFreePosition = newFreePosition;

            return position;
        } finally {
            cache.writeLock.unlock();
        }
    }
