    public DataFileCache(Database db, String baseFileName, boolean defrag) {

        initParams(db, baseFileName, true);

        cache = new Cache(this);

        try {
            if (database.logger.isStoredFileAccess()) {
                dataFile = RAFile.newScaledRAFile(database, dataFileName,
                                                  false,
                                                  RAFile.DATA_FILE_STORED);
            } else {
                dataFile = new RAFileSimple(database.logger, dataFileName,
                                            "rw");
            }
        } catch (Throwable t) {
            throw Error.error(ErrorCode.FILE_IO_ERROR, t);
        }

        initNewFile();
        initBuffers();

        if (database.logger.getDataFileSpaces() > 0) {
            spaceManager = new DataSpaceManagerBlocks(this);
        } else {
            spaceManager = new DataSpaceManagerSimple(this, false);
        }
    }
