    synchronized public Session newSysSession() {

        Session session = new Session(sysSession.database,
                                      sysSession.getUser(), false, false,
                                      sessionIdCount, null, 0);

        session.currentSchema =
            sysSession.database.schemaManager.getDefaultSchemaHsqlName();

        sessionMap.put(sessionIdCount, session);

        sessionIdCount++;

        return session;
    }
