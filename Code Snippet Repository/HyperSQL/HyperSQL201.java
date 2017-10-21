    public void beginAction(Session session, Statement cs) {

        writeLock.lock();

        try {
            if (hasExpired) {
                session.redoAction = true;

                return;
            }

            cs = updateCurrentStatement(session, cs);

            if (cs == null) {
                return;
            }

            boolean canProceed = setWaitedSessionsTPL(session, cs);

            if (canProceed) {
                session.isPreTransaction = true;

                if (session.tempSet.isEmpty()) {
                    lockTablesTPL(session, cs);

                    // we don't set other sessions that would now be waiting for this one too
                    // next lock release will do it
                } else {
                    setWaitingSessionTPL(session);
                }
            }
        } finally {
            writeLock.unlock();
        }
    }
