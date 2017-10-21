    public void beginTransaction(Session session) {

        writeLock.lock();

        try {
            if (!session.isTransaction) {
                session.actionTimestamp      = getNextGlobalChangeTimestamp();
                session.transactionTimestamp = session.actionTimestamp;
                session.isPreTransaction     = false;
                session.isTransaction        = true;

                transactionCount++;

                liveTransactionTimestamps.addLast(
                    session.transactionTimestamp);
            }
        } finally {
            writeLock.unlock();
        }
    }
