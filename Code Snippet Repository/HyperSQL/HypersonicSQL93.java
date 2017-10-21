    public static Session getSession(int dbId, long sessionId) {

        Database db = null;

        synchronized (databaseIDMap) {
            db = (Database) databaseIDMap.get(dbId);
        }

        return db == null ? null
                          : db.sessionManager.getSession(sessionId);
    }
