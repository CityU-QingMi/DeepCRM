    public void setReadOnly(boolean readonly) {

        if (!readonly && database.databaseReadOnly) {
            throw Error.error(ErrorCode.DATABASE_IS_READONLY);
        }

        if (isInMidTransaction()) {
            throw Error.error(ErrorCode.X_25001);
        }

        sessionContext.isReadOnly = readonly ? Boolean.TRUE
                                             : Boolean.FALSE;
    }
