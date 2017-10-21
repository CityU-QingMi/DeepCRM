    StatementDMQL compileTriggerSetStatement(Table table,
            RangeGroup[] rangeGroups) {

        Expression[]   updateExpressions;
        int[]          columnMap;
        OrderedHashSet targetSet = new OrderedHashSet();
        HsqlArrayList  exprList  = new HsqlArrayList();
        RangeVariable[] targetRangeVars = new RangeVariable[]{
            rangeGroups[0].getRangeVariables()[TriggerDef.NEW_ROW] };
        LongDeque colIndexList = new LongDeque();

        readSetClauseList(targetRangeVars, targetSet, colIndexList, exprList);

        columnMap = new int[colIndexList.size()];

        colIndexList.toArray(columnMap);

        Expression[] targets = new Expression[targetSet.size()];

        targetSet.toArray(targets);

        for (int i = 0; i < targets.length; i++) {
            resolveOuterReferencesAndTypes(RangeGroup.emptyArray, targets[i]);
        }

        updateExpressions = new Expression[exprList.size()];

        exprList.toArray(updateExpressions);
        resolveUpdateExpressions(table, RangeGroup.emptyGroup, columnMap,
                                 updateExpressions, rangeGroups);

        StatementDMQL cs = new StatementSet(session, targets, table,
                                            rangeGroups[0].getRangeVariables(),
                                            columnMap, updateExpressions,
                                            compileContext);

        return cs;
    }
