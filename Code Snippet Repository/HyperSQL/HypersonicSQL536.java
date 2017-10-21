    void resolveColumnReferencesInOrderBy(Session session,
                                          RangeGroup[] rangeGroups,
                                          SortAndSlice sortAndSlice) {

        // replace the aliases with expressions
        // replace column names with expressions and resolve the table columns
        int orderCount = sortAndSlice.getOrderLength();

        for (int i = 0; i < orderCount; i++) {
            ExpressionOrderBy e =
                (ExpressionOrderBy) sortAndSlice.exprList.get(i);

            replaceColumnIndexInOrderBy(e);

            if (e.getLeftNode().queryTableColumnIndex != -1) {
                continue;
            }

            if (sortAndSlice.sortUnion) {
                if (e.getLeftNode().getType() != OpTypes.COLUMN) {
                    throw Error.error(ErrorCode.X_42576);
                }
            }

            e.replaceAliasInOrderBy(session, exprColumns, indexLimitVisible);
            resolveColumnReferencesAndAllocate(session, e,
                                               rangeVariables.length,
                                               RangeGroup.emptyArray, false);

            if (isAggregated || isGrouped) {
                boolean check = e.getLeftNode().isComposedOf(exprColumns, 0,
                    indexLimitVisible + groupByColumnCount,
                    Expression.aggregateFunctionSet);

                if (!check) {
                    throw Error.error(ErrorCode.X_42576);
                }
            }
        }

        if (sortAndSlice.limitCondition != null) {
            unresolvedExpressions =
                sortAndSlice.limitCondition.resolveColumnReferences(session,
                    this, rangeGroups, unresolvedExpressions);
        }

        sortAndSlice.prepare(indexStartOrderBy);
    }
