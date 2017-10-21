    public static synchronized Database lookupDatabaseObject(DatabaseType type,
            String path) {

        Object  key = path;
        HashMap databaseMap;

        if (type == DatabaseType.DB_FILE) {
            databaseMap = fileDatabaseMap;
            key         = filePathToKey(path);
        } else if (type == DatabaseType.DB_RES) {
            databaseMap = resDatabaseMap;
        } else if (type == DatabaseType.DB_MEM) {
            databaseMap = memDatabaseMap;
        } else {
            throw (Error.runtimeError(ErrorCode.U_S0500, "DatabaseManager"));
        }

        return (Database) databaseMap.get(key);
    }
