    synchronized final int getDBIndex(String aliasPath) {

        int    semipos  = aliasPath.indexOf(';');
        String alias    = aliasPath;
        String filepath = null;

        if (semipos != -1) {
            alias    = aliasPath.substring(0, semipos);
            filepath = aliasPath.substring(semipos + 1);
        }

        int dbIndex = ArrayUtil.find(dbAlias, alias);

        if (dbIndex == -1) {
            if (filepath == null) {
                HsqlException e = Error.error(ErrorCode.GENERAL_ERROR,
                                              "database alias does not exist");

                printError("database alias=" + alias + " does not exist");
                setServerError(e);

                throw e;
            } else {
                return openDatabase(alias, filepath);
            }
        } else {
            return dbIndex;
        }
    }
