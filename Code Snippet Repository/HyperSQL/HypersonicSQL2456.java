    protected void initParams(Database database, String baseFileName,
                              boolean defrag) {

        this.dataFileName = baseFileName + ".data.tmp";
        this.database     = database;
        fa                = FileUtil.getFileUtil();
        dataFileScale     = 64;
        cachedRowPadding  = dataFileScale;
        initialFreePos    = dataFileScale;
        maxCacheRows      = 2048;
        maxCacheBytes     = maxCacheRows * 1024L;
        maxDataFileSize   = (long) Integer.MAX_VALUE * dataFileScale;
    }
