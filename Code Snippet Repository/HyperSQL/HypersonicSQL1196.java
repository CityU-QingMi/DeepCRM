    void resetLocks(Session session) {

        final int waitingCount = session.waitingSessions.size();

        for (int i = 0; i < waitingCount; i++) {
            Session current = (Session) session.waitingSessions.get(i);

            current.tempUnlocked = false;

            long count = current.latch.getCount();

            if (count == 1) {
                boolean canProceed = setWaitedSessionsTPL(current,
                    current.sessionContext.currentStatement);

                if (canProceed) {
                    if (current.tempSet.isEmpty()) {
                        lockTablesTPL(current,
                                      current.sessionContext.currentStatement);

                        current.tempUnlocked = true;
                    }
                }
            }
        }

        for (int i = 0; i < waitingCount; i++) {
            Session current = (Session) session.waitingSessions.get(i);

            if (current.tempUnlocked) {

                //
            } else if (current.abortTransaction) {

                //
            } else {

                // this can introduce additional waits for the sessions
                setWaitedSessionsTPL(current,
                                     current.sessionContext.currentStatement);
            }
        }
    }
