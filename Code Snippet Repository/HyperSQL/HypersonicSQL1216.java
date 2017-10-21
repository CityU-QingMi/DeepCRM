    public void rollback(Session session) {

        writeLock.lock();

        try {
            session.abortTransaction        = false;
            session.actionTimestamp         = getNextGlobalChangeTimestamp();
            session.transactionEndTimestamp = session.actionTimestamp;

            rollbackPartial(session, 0, session.transactionTimestamp);
            endTransaction(session);
            session.logSequences();
            endTransactionTPL(session);

            session.isTransaction = false;

            countDownLatches(session);
        } finally {
            writeLock.unlock();
        }
    }
