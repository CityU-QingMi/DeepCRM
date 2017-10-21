    public long enlargeFileSpace(long delta) {

        writeLock.lock();

        try {
            long position = fileFreePosition;

            if (position + delta > maxDataFileSize) {
                logSevereEvent("data file reached maximum allowed size: "
                               + dataFileName + " " + maxDataFileSize, null);

                throw Error.error(ErrorCode.DATA_FILE_IS_FULL);
            }

            boolean result = dataFile.ensureLength(position + delta);

            if (!result) {
                logSevereEvent("data file cannot be enlarged - disk space: "
                               + dataFileName + " "
                               + (position + delta), null);

                throw Error.error(ErrorCode.DATA_FILE_IS_FULL);
            }

            fileFreePosition += delta;

            return position;
        } finally {
            writeLock.unlock();
        }
    }
