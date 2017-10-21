    public boolean prepareCommitActions(Session session) {

        if (session.abortTransaction) {

//            System.out.println("cascade fail " + session + " " + session.actionTimestamp);
            return false;
        }

        writeLock.lock();

        try {
            int limit = session.rowActionList.size();

            for (int i = 0; i < limit; i++) {
                RowAction action = (RowAction) session.rowActionList.get(i);

                if (!action.canCommit(session, session.actionSet)) {

//                System.out.println("commit conflicts " + session + " " + session.actionTimestamp);
                    return false;
                }
            }

            session.actionTimestamp = getNextGlobalChangeTimestamp();

            for (int i = 0; i < limit; i++) {
                RowAction action = (RowAction) session.rowActionList.get(i);

                action.prepareCommit(session);
            }

            for (int i = 0; i < session.actionSet.size(); i++) {
                Session current =
                    ((RowActionBase) session.actionSet.get(i)).session;

                current.abortTransaction = true;
            }

            return true;
        } finally {
            writeLock.unlock();
            session.actionSet.clear();
        }
    }
