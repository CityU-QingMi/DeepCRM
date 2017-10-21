    private static synchronized void addDatabaseObject(DatabaseType type,
            String path, Database db) {

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
            throw Error.runtimeError(ErrorCode.U_S0500, "DatabaseManager");
        }

        synchronized (databaseIDMap) {
            databaseIDMap.put(db.databaseID, db);
        }

        databaseMap.put(key, db);
    }
