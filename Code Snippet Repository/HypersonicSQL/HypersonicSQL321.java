    private Statement compileLock() {

        read();

        if (readIfThis(Tokens.CATALOG)) {
            return compileLockCatalog();
        } else {
            readThis(Tokens.TABLE);

            return compileLockTable();
        }
    }
