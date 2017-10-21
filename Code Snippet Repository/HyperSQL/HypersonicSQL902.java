    public synchronized Session newSession(Database db, User user,
                                           boolean readonly,
                                           boolean autoCommit,
                                           String zoneString,
                                           int timeZoneSeconds) {

        Session s = new Session(db, user, autoCommit, readonly,
                                sessionIdCount, zoneString, timeZoneSeconds);

        sessionMap.put(sessionIdCount, s);

        sessionIdCount++;

        return s;
    }
