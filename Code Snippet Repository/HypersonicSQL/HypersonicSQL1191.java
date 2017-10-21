    boolean checkDeadlock(Session session, OrderedHashSet newWaits) {

        int size = session.waitingSessions.size();

        for (int i = 0; i < size; i++) {
            Session current = (Session) session.waitingSessions.get(i);

            if (newWaits.contains(current)) {
                return false;
            }

            if (!checkDeadlock(current, newWaits)) {
                return false;
            }
        }

        return true;
    }
