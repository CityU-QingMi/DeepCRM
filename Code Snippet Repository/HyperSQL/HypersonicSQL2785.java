    protected void initParams(Database database, String fileSettingsString,
                              boolean defrag) {

        this.database    = database;
        fa               = FileUtil.getFileUtil();
        textFileSettings = new TextFileSettings(database, fileSettingsString);
        dataFileName     = textFileSettings.getFileName();

        if (dataFileName == null) {
            throw Error.error(ErrorCode.X_S0501);
        }

        dataFileName  = ((FileUtil) fa).canonicalOrAbsolutePath(dataFileName);
        maxCacheRows  = textFileSettings.getMaxCacheRows();
        maxCacheBytes = textFileSettings.getMaxCacheBytes();

        // max size is 256G
        maxDataFileSize  = (long) Integer.MAX_VALUE * Logger.largeDataFactor;
        cachedRowPadding = 1;
        dataFileScale    = 1;
    }
