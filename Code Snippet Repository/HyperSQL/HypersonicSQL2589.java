    public LobStoreRAFile(Database database, int lobBlockSize) {

        this.database     = database;
        this.lobBlockSize = lobBlockSize;
        this.fileName     = database.getPath() + ".lobs";

        try {
            boolean exists =
                database.logger.getFileAccess().isStreamElement(fileName);

            if (exists) {
                openFile();
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.DATA_FILE_ERROR, t);
        }
    }
