    Result executeTriggerSetStatement(Session session) {

        Table        table          = targetTable;
        int[]        colMap         = updateColumnMap;    // column map
        Expression[] colExpressions = updateExpressions;
        Type[]       colTypes       = table.getColumnTypes();
        int index = targetRangeVariables[TriggerDef.NEW_ROW].rangePosition;
        Object[]     oldData = session.sessionContext.triggerArguments[index];
        Object[] data = StatementDML.getUpdatedData(session, targets, table,
            colMap, colExpressions, colTypes, oldData);

        ArrayUtil.copyArray(data, oldData, data.length);

        return Result.updateOneResult;
    }
