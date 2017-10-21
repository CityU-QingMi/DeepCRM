    void getTransactionSessions(Session session) {

        OrderedHashSet set      = session.tempSet;
        Session[]      sessions = database.sessionManager.getAllSessions();

        for (int i = 0; i < sessions.length; i++) {
            long timestamp = sessions[i].transactionTimestamp;

            if (session != sessions[i] && sessions[i].isTransaction) {
                set.add(sessions[i]);
            }
        }
    }
