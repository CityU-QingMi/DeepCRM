    void endTransactionTPL(Session session) {

        if (catalogWriteSession != session) {
            return;
        }

        //
        Session nextSession = null;

        for (int i = 0; i < session.waitingSessions.size(); i++) {
            Session   current = (Session) session.waitingSessions.get(i);
            Statement st      = current.sessionContext.currentStatement;

            if (st != null && st.isCatalogLock(txModel)) {
                nextSession = current;

                break;
            }
        }

        if (nextSession == null) {
            catalogWriteSession = null;
            isLockedMode        = false;
        } else {
            for (int i = 0; i < session.waitingSessions.size(); i++) {
                Session current = (Session) session.waitingSessions.get(i);

                if (current != nextSession) {
                    current.waitedSessions.add(nextSession);
                    nextSession.waitingSessions.add(current);
                    current.latch.setCount(current.waitedSessions.size());
                }
            }

            catalogWriteSession = nextSession;
        }

        unlockTxTs      = session.actionTimestamp;
        unlockSessionId = session.getId();
    }
