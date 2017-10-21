    public boolean prepareCommitActions(Session session) {

        writeLock.lock();

        try {
            int limit = session.rowActionList.size();

            session.actionTimestamp = getNextGlobalChangeTimestamp();

            for (int i = 0; i < limit; i++) {
                RowAction action = (RowAction) session.rowActionList.get(i);

                action.prepareCommit(session);
            }

            return true;
        } finally {
            writeLock.unlock();
        }
    }
