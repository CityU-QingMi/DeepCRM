    public void beginActionResume(Session session) {

        writeLock.lock();

        try {
            session.actionTimestamp      = getNextGlobalChangeTimestamp();
            session.actionStartTimestamp = session.actionTimestamp;

            if (!session.isTransaction) {
                session.transactionTimestamp = session.actionTimestamp;
                session.isPreTransaction     = false;
                session.isTransaction        = true;

                liveTransactionTimestamps.addLast(
                    session.transactionTimestamp);

                transactionCount++;
            }
        } finally {
            writeLock.unlock();
        }
    }
