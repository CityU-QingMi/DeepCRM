    private static RandomAccessInterface getStorage(Database database,
            String pathName, String openMode) throws IOException {

        if (database.logger.isStoredFileAccess()) {
            return RAFile.newScaledRAFile(database, pathName,
                                          openMode.equals("r"),
                                          RAFile.DATA_FILE_STORED);
        } else {
            return new RAFileSimple(database.logger, pathName, openMode);
        }
    }
