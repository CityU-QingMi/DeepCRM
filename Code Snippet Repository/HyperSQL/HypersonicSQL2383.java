    public RowSetNavigatorDataTable(Session session,
                                    QueryExpression queryExpression) {

        super(session);

        table              = queryExpression.resultTable.duplicate();
        visibleColumnCount = table.getColumnCount();
        store = session.sessionData.getNewResultRowStore(table, true);
        table.store        = store;
        mainIndex          = queryExpression.mainIndex;
        fullIndex          = queryExpression.fullIndex;
    }
