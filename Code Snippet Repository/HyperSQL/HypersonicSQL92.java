    public static Session newSession(int dbID, String user, String password,
                                     String zoneString, int timeZoneSeconds) {

        Database db = null;

        synchronized (databaseIDMap) {
            db = (Database) databaseIDMap.get(dbID);
        }

        if (db == null) {
            return null;
        }

        Session session = db.connect(user, password, zoneString,
                                     timeZoneSeconds);

        session.isNetwork = true;

        return session;
    }
