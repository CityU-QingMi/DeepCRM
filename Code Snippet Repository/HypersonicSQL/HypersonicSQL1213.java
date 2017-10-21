    void endTransaction(Session session) {

        long timestamp = session.transactionTimestamp;
        int  index     = liveTransactionTimestamps.indexOf(timestamp);

        if (index >= 0) {
            transactionCount--;

            liveTransactionTimestamps.remove(index);
            mergeExpiredTransactions(session);
        }
    }
