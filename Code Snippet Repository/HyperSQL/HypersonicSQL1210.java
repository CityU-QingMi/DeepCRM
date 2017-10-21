    public void beginTransaction(Session session) {

        writeLock.lock();

        try {
            if (!session.isTransaction) {
                session.actionTimestamp      = getNextGlobalChangeTimestamp();
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
