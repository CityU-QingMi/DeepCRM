    static void backupFile(Database database, String fileName,
                           String backupFileName, boolean newFile) {

        try {
            FileAccess fa = database.logger.getFileAccess();

            if (database.logger.propIncrementBackup) {
                if (fa.isStreamElement(backupFileName)) {
                    deleteFile(database, backupFileName);

                    if (fa.isStreamElement(backupFileName)) {
                        throw Error.error(ErrorCode.DATA_FILE_ERROR,
                                          "cannot delete old backup file");
                    }
                }

                return;
            }

            if (fa.isStreamElement(fileName)) {
                if (newFile) {
                    backupFileName += Logger.newFileExtension;
                } else {
                    deleteFile(database, backupFileName);

                    if (fa.isStreamElement(backupFileName)) {
                        throw Error.error(ErrorCode.DATA_FILE_ERROR,
                                          "cannot delete old backup file");
                    }
                }

                FileArchiver.archive(fileName, backupFileName, fa,
                                     FileArchiver.COMPRESSION_ZIP);
            }
        } catch (Throwable t) {
            database.logger.logSevereEvent("DataFileCache.backupFile", t);

            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
