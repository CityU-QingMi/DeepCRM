    void prepareReset(Session session) {

        OrderedHashSet waitedSessions = session.waitedSessions;

        for (int i = 0; i < waitedSessions.size(); i++) {
            Session current = (Session) waitedSessions.get(i);

            current.waitingSessions.remove(session);
        }

        waitedSessions.clear();
    }
