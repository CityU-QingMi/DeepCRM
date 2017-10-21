    public void open(boolean readonly) {

        try {
            dataFile = new RAFile(database.logger, dataFileName, false, false,
                                  false);
            fileFreePosition = initialFreePos;

            initBuffers();

            spaceManager = new DataSpaceManagerSimple(this, false);
        } catch (Throwable t) {
            database.logger.logWarningEvent("Failed to open Session RA file",
                                            t);
            release();

            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_DataFileCache_open, new Object[] {
                t.toString(), dataFileName
            });
        }
    }
