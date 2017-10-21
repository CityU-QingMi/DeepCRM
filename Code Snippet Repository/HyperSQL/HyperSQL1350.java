    public DbBackup(File archiveFile, String dbPath, boolean script) {

        this.archiveFile = archiveFile;

        File dbPathFile = new File(dbPath);

        dbDir        = dbPathFile.getAbsoluteFile().getParentFile();
        instanceName = dbPathFile.getName();
        componentFiles = new File[]{
            new File(dbDir, instanceName + ".script"), };
        componentStreams = new InputStreamInterface[componentFiles.length];
        existList        = new boolean[componentFiles.length];
        ignoreList       = new boolean[componentFiles.length];
        abortUponModify  = false;
    }
