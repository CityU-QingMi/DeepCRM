    private boolean restoreBackup() {

        try {
            FileAccess fileAccess = database.logger.getFileAccess();

            // todo - in case data file cannot be deleted, reset it
            deleteFile(database, dataFileName);

            if (fileAccess.isStreamElement(backupFileName)) {
                FileArchiver.unarchive(backupFileName, dataFileName,
                                       fileAccess,
                                       FileArchiver.COMPRESSION_ZIP);

                return true;
            }

            return false;
        } catch (Throwable t) {
            database.logger.logSevereEvent("DataFileCache.restoreBackup", t);

            throw Error.error(t, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_Message_Pair, new Object[] {
                t.toString(), backupFileName
            });
        }
    }
