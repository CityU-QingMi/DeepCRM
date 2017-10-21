    void setWaitingSessionTPL(Session session) {

        int count = session.tempSet.size();

        assert session.latch.getCount() <= count + 1;

        for (int i = 0; i < count; i++) {
            Session current = (Session) session.tempSet.get(i);

            current.waitingSessions.add(session);
        }

        session.tempSet.clear();
        session.latch.setCount(count);
    }
