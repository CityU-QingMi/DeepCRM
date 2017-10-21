    protected void initParams(Database database, String baseFileName,
                              boolean defrag) {

        this.dataFileName   = baseFileName + Logger.dataFileExtension;
        this.backupFileName = baseFileName + Logger.backupFileExtension;
        this.database       = database;
        fa                  = database.logger.getFileAccess();
        dataFileScale       = database.logger.getDataFileScale();
        cachedRowPadding    = 8;

        if (dataFileScale > 8) {
            cachedRowPadding = dataFileScale;
        }

        initialFreePos = MIN_INITIAL_FREE_POS;

        if (initialFreePos < dataFileScale) {
            initialFreePos = dataFileScale;
        }

        cacheReadonly = database.isFilesReadOnly();
        maxCacheRows  = database.logger.getCacheMaxRows();
        maxCacheBytes = database.logger.getCacheSize();
        maxDataFileSize = (long) Integer.MAX_VALUE * dataFileScale
                          * database.logger.getDataFileFactor();

        if (defrag) {
            this.dataFileName   = dataFileName + Logger.newFileExtension;
            this.backupFileName = backupFileName + Logger.newFileExtension;
            this.maxCacheRows   = 1024;
            this.maxCacheBytes  = 1024 * 4096;
        }
    }
