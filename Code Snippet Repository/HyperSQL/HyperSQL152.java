    public void materialise(Session session) {

        session.sessionContext.pushStatementState();

        try {
            PersistentStore store;

            // table constructors
            if (dataExpression != null) {
                store = session.sessionData.getSubqueryRowStore(this);

                dataExpression.insertValuesIntoSubqueryTable(session, store);

                return;
            }

            if (queryExpression == null) {
                return;
            }

            Result result;

            result = queryExpression.getResult(session, 0);

            if (uniqueRows) {
                RowSetNavigatorData navigator =
                    ((RowSetNavigatorData) result.getNavigator());

                navigator.removeDuplicates(session);
            }

            store = session.sessionData.getSubqueryRowStore(this);

            insertResult(session, store, result);
            result.getNavigator().release();
        } finally {
            session.sessionContext.popStatementState();
        }
    }
