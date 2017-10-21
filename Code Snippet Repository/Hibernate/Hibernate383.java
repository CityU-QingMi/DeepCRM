    private void doInSession(String tenant, Consumer<Session> function) {
        Session session = null;
        Transaction txn = null;
        try {
            session = sessionFactory
                .withOptions()
                .tenantIdentifier( tenant )
                .openSession();
            txn = session.getTransaction();
            txn.begin();
            function.accept(session);
            txn.commit();
        } catch (Throwable e) {
            if ( txn != null ) txn.rollback();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
