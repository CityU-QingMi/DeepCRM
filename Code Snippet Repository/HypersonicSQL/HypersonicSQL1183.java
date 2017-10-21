    void resetLatchesMidTransaction(Session session) {

        session.tempSet.clear();
        session.tempSet.addAll(session.waitingSessions);
        session.waitingSessions.clear();

        final int waitingCount = session.tempSet.size();

        for (int i = 0; i < waitingCount; i++) {
            Session current  = (Session) session.tempSet.get(i);
            boolean testCode = false;

            if (testCode) {
                if (!current.abortTransaction && current.tempSet.isEmpty()) {

                    // test code valid for top level statements
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

        session.tempSet.clear();
    }
