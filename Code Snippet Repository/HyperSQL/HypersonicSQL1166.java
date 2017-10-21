    public void setDataReadOnly(boolean value) {

        if (!value) {
            if (isReversed) {
                throw Error.error(ErrorCode.DATA_IS_READONLY);
            }

            if (database.isFilesReadOnly()) {
                throw Error.error(ErrorCode.DATABASE_IS_READONLY);
            }

            if (isConnected()) {
                store.getCache().close();
                store.getCache().open(value);
            }
        }

        isReadOnly = value;
    }
