    static void renameBackupFile(Database database, String backupFileName) {

        FileAccess fileAccess = database.logger.getFileAccess();

        if (database.logger.propIncrementBackup) {
            deleteFile(database, backupFileName);

            return;
        }

        if (fileAccess.isStreamElement(backupFileName
                                       + Logger.newFileExtension)) {
            deleteFile(database, backupFileName);
            fileAccess.renameElement(backupFileName + Logger.newFileExtension,
                                     backupFileName);
        }
    }
