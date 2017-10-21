    public int getDefaultWriteDelay() {

        // OOo related code
        if (database.logger.isStoredFileAccess()) {
            return 2000;
        }

        // OOo end
        return 500;
    }
