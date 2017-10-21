    public void beginTransaction(Session session) {

        if (!session.isTransaction) {
            session.actionTimestamp      = getNextGlobalChangeTimestamp();
            session.transactionTimestamp = session.actionTimestamp;
            session.isPreTransaction     = false;
            session.isTransaction        = true;

            transactionCount++;
        }
    }
