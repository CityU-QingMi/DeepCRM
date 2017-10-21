    public void endAction(Result result) {

//        tempActionHistory.add("endAction " + actionTimestamp);
        abortAction = false;

        sessionData.persistentStoreCollection.clearStatementTables();

        if (result.mode == ResultConstants.ERROR) {
            sessionData.persistentStoreCollection.clearResultTables(
                actionTimestamp);
            database.txManager.rollbackAction(this);
        } else {
            sessionContext
                .diagnosticsVariables[ExpressionColumn.idx_row_count] =
                    result.mode == ResultConstants.UPDATECOUNT
                    ? Integer.valueOf(result.getUpdateCount())
                    : ValuePool.INTEGER_0;

            database.txManager.completeActions(this);
        }

//        tempActionHistory.add("endAction ends " + actionTimestamp);
    }
