    public RAFileHybrid(Database database, String name,
                        boolean readOnly) throws IOException {

        this.database   = database;
        this.fileName   = name;
        this.isReadOnly = readOnly;

        long         fileLength;
        java.io.File fi = new java.io.File(name);

        fileLength = fi.length();

        newStore(fileLength);
    }
