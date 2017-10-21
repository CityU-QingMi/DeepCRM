    Result insertSingleRow(Session session, PersistentStore store,
                           Object[] data) {

        session.sessionData.startRowProcessing();
        baseTable.setIdentityColumn(session, data);

        if (baseTable.triggerLists[Trigger.INSERT_BEFORE_ROW].length > 0) {
            baseTable.fireTriggers(session, Trigger.INSERT_BEFORE_ROW, null,
                                   data, null);
        }

        baseTable.insertSingleRow(session, store, data, null);
        performIntegrityChecks(session, baseTable, null, data, null);

        if (baseTable.triggerLists[Trigger.INSERT_AFTER_ROW].length > 0) {
            baseTable.fireTriggers(session, Trigger.INSERT_AFTER_ROW, null,
                                   data, null);
        }

        if (baseTable.triggerLists[Trigger.INSERT_AFTER].length > 0) {
            baseTable.fireTriggers(session, Trigger.INSERT_AFTER,
                                   (RowSetNavigator) null);
        }

        session.sessionContext
            .diagnosticsVariables[ExpressionColumn.idx_row_count] =
                Integer.valueOf(1);

        return Result.updateOneResult;
    }
