    public void close() {

        writeLock.lock();

        try {
            clear();

            if (dataFile != null) {
                dataFile.close();

                dataFile = null;

                fa.removeElement(dataFileName);
            }
        } catch (Throwable t) {
            database.logger.logWarningEvent("Failed to close Session RA file",
                                            t);

            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_DataFileCache_close, new Object[] {
                t.toString(), dataFileName
            });
        } finally {
            writeLock.unlock();
        }
    }
