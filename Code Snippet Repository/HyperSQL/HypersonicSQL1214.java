    private void countDownLatches(Session session) {

        for (int i = 0; i < session.waitingSessions.size(); i++) {
            Session current = (Session) session.waitingSessions.get(i);

            current.waitedSessions.remove(session);
            current.latch.setCount(current.waitedSessions.size());
        }

        // waitedSessions is not empty if the latch is zeroed by a
        // different administrative session
        session.waitedSessions.clear();
        session.waitingSessions.clear();
    }
