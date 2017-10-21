    public synchronized void setReadOnlyDefault(boolean readonly) {

        if (!readonly && database.databaseReadOnly) {
            throw Error.error(ErrorCode.DATABASE_IS_READONLY);
        }

        isReadOnlyDefault = readonly;

        if (!isInMidTransaction()) {
            sessionContext.isReadOnly = isReadOnlyDefault ? Boolean.TRUE
                                                          : Boolean.FALSE;
        }
    }
