    public SimpleLog(String path, int level, boolean isSQL) {

        this.isSystem = path == null;
        this.filePath = path;
        this.isSQL    = isSQL;
        logTypeNames  = isSQL ? sqlLogTypeNames
                              : appLogTypeNames;
        sb            = new StringBuffer(256);

        setLevel(level);
    }
