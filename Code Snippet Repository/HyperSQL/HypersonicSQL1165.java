    public void checkDataReadOnly() {

        if (dataSource.length() == 0) {
            String name = getName().getSchemaQualifiedStatementName();

            throw Error.error(ErrorCode.TEXT_TABLE_UNKNOWN_DATA_SOURCE, name);
        }

        if (isDataReadOnly()) {
            throw Error.error(ErrorCode.DATA_IS_READONLY);
        }
    }
