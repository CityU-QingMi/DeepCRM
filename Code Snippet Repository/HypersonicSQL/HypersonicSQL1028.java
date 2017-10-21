    public void setDataReadOnly(boolean value) {

        // Changing the Read-Only mode for the table is only allowed if the
        // the database can realize it.
        if (!value) {
            if (database.isFilesReadOnly() && isFileBased()) {
                throw Error.error(ErrorCode.DATA_IS_READONLY);
            } else if (database.getType() == DatabaseType.DB_MEM && isText) {
                throw Error.error(ErrorCode.DATA_IS_READONLY);
            }
        }

        isReadOnly = value;
    }
