    private boolean restoreBackupIncremental() {

        try {
            FileAccess fileAccess = database.logger.getFileAccess();

            if (fileAccess.isStreamElement(backupFileName)) {
                RAShadowFile.restoreFile(database, backupFileName,
                                         dataFileName);
                deleteFile(database, backupFileName);

                return true;
            }

            // this is an anomaly where no backup exists but .data file
            // modified flag has been set
            return false;
        } catch (Throwable e) {
            database.logger.logSevereEvent(
                "DataFileCache.restoreBackupIncremental", e);

            throw Error.error(ErrorCode.FILE_IO_ERROR, e);
        }
    }
