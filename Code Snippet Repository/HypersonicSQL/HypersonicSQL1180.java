    public void beginActionResume(Session session) {

        session.actionTimestamp      = getNextGlobalChangeTimestamp();
        session.actionStartTimestamp = session.actionTimestamp;

        if (!session.isTransaction) {
            session.transactionTimestamp = session.actionTimestamp;
            session.isPreTransaction     = false;
            session.isTransaction        = true;

            transactionCount++;
        }
    }
