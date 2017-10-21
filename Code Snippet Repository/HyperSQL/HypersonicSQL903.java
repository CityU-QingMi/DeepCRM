    public synchronized void resetLoggedSchemas() {

        Iterator it = sessionMap.values().iterator();

        for (int i = 0; it.hasNext(); i++) {
            Session session = (Session) it.next();

            session.loggedSchema = null;
        }

        this.sysLobSession.loggedSchema = null;
    }
