    static void deleteFile(Database database, String fileName) {

        FileAccess fileAccess = database.logger.getFileAccess();

        // first attempt to delete
        fileAccess.removeElement(fileName);

        if (database.logger.isStoredFileAccess()) {
            return;
        }

        if (fileAccess.isStreamElement(fileName)) {
            database.logger.log.deleteOldDataFiles();
            fileAccess.removeElement(fileName);

            if (fileAccess.isStreamElement(fileName)) {
                String discardName = FileUtil.newDiscardFileName(fileName);

                fileAccess.renameElement(fileName, discardName);
            }
        }
    }
