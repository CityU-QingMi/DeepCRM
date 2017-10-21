    public DbBackup(File archiveFile, String dbPath) {

        this.archiveFile = archiveFile;

        File dbPathFile = new File(dbPath);

        dbDir          = dbPathFile.getAbsoluteFile().getParentFile();
        instanceName   = dbPathFile.getName();
        componentFiles = new File[] {
            new File(dbDir, instanceName + ".properties"),
            new File(dbDir, instanceName + ".script"),
            new File(dbDir, instanceName + ".data"),
            new File(dbDir, instanceName + ".backup"),
            new File(dbDir, instanceName + ".log"),
            new File(dbDir, instanceName + ".lobs")
        };
        componentStreams = new InputStreamInterface[componentFiles.length];
        existList        = new boolean[componentFiles.length];
        ignoreList       = new boolean[componentFiles.length];
    }
