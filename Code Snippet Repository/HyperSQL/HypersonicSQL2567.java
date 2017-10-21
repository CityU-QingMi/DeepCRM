    public void open() {

        lobBlockSize = database.logger.getLobBlockSize();
        cryptLobs    = database.logger.cryptLobs;
        compressLobs = database.logger.propCompressLobs;

        if (compressLobs || cryptLobs) {
            int largeBufferBlockSize = largeLobBlockSize + 4 * 1024;

            inflater   = new Inflater();
            deflater   = new Deflater(Deflater.BEST_SPEED);
            dataBuffer = new byte[largeBufferBlockSize];
        }

        if (database.getType() == DatabaseType.DB_RES) {
            lobStore = new LobStoreInJar(database, lobBlockSize);
        } else if (database.getType() == DatabaseType.DB_FILE) {
            lobStore = new LobStoreRAFile(database, lobBlockSize);

            if (!database.isFilesReadOnly()) {
                byteBuffer = new byte[lobBlockSize];

                initialiseLobSpace();
            }
        } else {
            lobStore   = new LobStoreMem(lobBlockSize);
            byteBuffer = new byte[lobBlockSize];

            initialiseLobSpace();
        }
    }
