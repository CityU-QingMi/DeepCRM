    static void removeDatabase(Database database) {

        int          dbID = database.databaseID;
        DatabaseType type = database.getType();
        String       path = database.getPath();
        Object       key  = path;
        HashMap      databaseMap;

        notifyServers(database);

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

        boolean isEmpty = false;

        synchronized (databaseIDMap) {
            databaseIDMap.remove(dbID);

            isEmpty = databaseIDMap.isEmpty();
        }

        synchronized (databaseMap) {
            databaseMap.remove(key);
        }

        if (isEmpty) {
            ValuePool.resetPool();
        }
    }
