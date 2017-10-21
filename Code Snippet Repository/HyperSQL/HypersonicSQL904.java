    public synchronized Session newSessionForLog(Database db) {

        boolean autoCommit = db.databaseProperties.isVersion18();
        Session s = new Session(db, db.getUserManager().getSysUser(),
                                autoCommit, false, sessionIdCount, null, 0);

        s.isProcessingLog = true;

        sessionMap.put(sessionIdCount, s);

        sessionIdCount++;

        return s;
    }
