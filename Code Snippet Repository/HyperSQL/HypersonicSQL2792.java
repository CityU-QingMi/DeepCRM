    public void open(boolean readonly) {

        fileFreePosition = 0;

        try {
            int type = database.getType() == DatabaseType.DB_RES
                       ? RAFile.DATA_FILE_JAR
                       : RAFile.DATA_FILE_TEXT;

            dataFile = RAFile.newScaledRAFile(database, dataFileName,
                                              readonly, type);
            fileFreePosition = dataFile.length();

            if (fileFreePosition > maxDataFileSize) {
                throw Error.error(ErrorCode.DATA_FILE_IS_FULL);
            }

            initBuffers();

            spaceManager = new DataSpaceManagerSimple(this, readonly);
        } catch (Throwable t) {
            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_TextCache_opening_file_error,
                              new Object[] {
                t.toString(), dataFileName
            });
        }

        cacheReadonly = readonly;
    }
