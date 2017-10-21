    private void checkModifyTable(boolean withContents) {

        if (session.getUser().isSystem()) {
            return;
        }

        if (session.isProcessingScript()) {
            return;
        }

        if (database.isFilesReadOnly() || table.isReadOnly()) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }

        if (table.isText() && table.isConnected()) {
            throw Error.error(ErrorCode.X_S0521);
        }
    }
