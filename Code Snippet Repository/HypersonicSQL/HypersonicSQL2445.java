    static void renameDataFile(Database database, String dataFileName) {

        FileAccess fileAccess = database.logger.getFileAccess();

        if (fileAccess.isStreamElement(dataFileName
                                       + Logger.newFileExtension)) {
            deleteFile(database, dataFileName);
            fileAccess.renameElement(dataFileName + Logger.newFileExtension,
                                     dataFileName);
        }
    }
