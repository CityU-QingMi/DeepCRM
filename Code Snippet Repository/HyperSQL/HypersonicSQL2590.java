    private void openFile() {

        try {
            boolean readonly = database.isFilesReadOnly();

            if (database.logger.isStoredFileAccess()) {
                file = RAFile.newScaledRAFile(database, fileName, readonly,
                                              RAFile.DATA_FILE_STORED);
            } else {
                file = new RAFileSimple(database.logger, fileName,
                                        readonly ? "r"
                                                 : "rws");
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
