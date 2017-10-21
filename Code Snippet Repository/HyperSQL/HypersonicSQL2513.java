    public void setDBModified(int mode) {

        String value;

        switch (mode) {

            case FILES_NOT_MODIFIED :
                value = MODIFIED_NO;
                break;

            case FILES_MODIFIED :
                value = MODIFIED_YES;
                break;

            case FILES_MODIFIED_NEW :
                value = MODIFIED_YES_NEW;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "HsqlDatabaseProperties");
        }

        stringProps.put(hsqldb_modified, value);
        save();
    }
