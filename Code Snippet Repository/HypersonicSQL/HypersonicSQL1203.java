    public void rollback(Session session) {

        writeLock.lock();

        try {
            session.abortTransaction        = false;
            session.actionTimestamp         = getNextGlobalChangeTimestamp();
            session.transactionEndTimestamp = session.actionTimestamp;

            rollbackPartial(session, 0, session.transactionTimestamp);
            endTransaction(session);
            session.logSequences();

            session.isTransaction = false;

            endTransactionTPL(session);
        } finally {
            writeLock.unlock();
        }
    }
