    public void prepareTable(Session session) {

        if (columnCount > 0) {
            return;
        }

        if (dataExpression != null) {
            if (columnCount == 0) {
                TableUtil.addAutoColumns(this, dataExpression.nodeDataTypes);
                setTableIndexesForSubquery(session);
            }
        }

        if (queryExpression != null) {
            columnList  = queryExpression.getColumns();
            columnCount = queryExpression.getColumnCount();

            setTableIndexesForSubquery(session);
        }
    }
