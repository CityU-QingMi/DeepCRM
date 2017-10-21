    void resetLatches(Session session) {

        final int waitingCount = session.waitingSessions.size();

        for (int i = 0; i < waitingCount; i++) {
            Session current  = (Session) session.waitingSessions.get(i);
            boolean testCode = false;

            if (testCode) {
                if (!current.abortTransaction && current.tempSet.isEmpty()) {

                    // test code valid only for top level statements
                    boolean hasLocks =
                        hasLocks(current,
                                 current.sessionContext.currentStatement);

                    if (!hasLocks) {
                        System.out.println("tx graph");

                        hasLocks =
                            hasLocks(current,
                                     current.sessionContext.currentStatement);
                    }
                }
            }

            setWaitingSessionTPL(current);
        }

        session.waitingSessions.clear();
        session.latch.setCount(0);
    }
