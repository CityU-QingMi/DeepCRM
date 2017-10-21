    public void closeAllSessions() {

        // don't disconnect system user; need it to save database
        Session[] sessions = getAllSessions();

        for (int i = 0; i < sessions.length; i++) {
            sessions[i].close();
        }

        synchronized(this) {
            sessionMap.clear();
        }
    }
