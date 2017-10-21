    boolean checkDeadlock(Session session, Session other) {

        int size = session.waitingSessions.size();

        for (int i = 0; i < size; i++) {
            Session current = (Session) session.waitingSessions.get(i);

            if (current == other) {
                return false;
            }

            if (!checkDeadlock(current, other)) {
                return false;
            }
        }

        return true;
    }
