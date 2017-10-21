    public void resolveRangeTable(Session session, RangeGroup rangeGroup,
                                  RangeGroup[] rangeGroups) {

        QueryExpression queryExpression = rangeTable.getQueryExpression();
        Expression      dataExpression  = rangeTable.getDataExpression();

        if (queryExpression == null && dataExpression == null) {
            return;
        }

        rangeGroups = (RangeGroup[]) ArrayUtil.toAdjustedArray(rangeGroups,
                rangeGroup, rangeGroups.length, 1);

        if (dataExpression != null) {
            HsqlList unresolved =
                dataExpression.resolveColumnReferences(session,
                    RangeGroup.emptyGroup, rangeGroups, null);

            unresolved = Expression.resolveColumnSet(session,
                    RangeVariable.emptyArray, RangeGroup.emptyArray,
                    unresolved);

            ExpressionColumn.checkColumnsResolved(unresolved);
            dataExpression.resolveTypes(session, null);
            setRangeTableVariables();
        }

        if (queryExpression != null) {
            queryExpression.resolveReferences(session, rangeGroups);

            HsqlList unresolved = queryExpression.getUnresolvedExpressions();

            unresolved = Expression.resolveColumnSet(session,
                    RangeVariable.emptyArray, RangeGroup.emptyArray,
                    unresolved);

            ExpressionColumn.checkColumnsResolved(unresolved);
            queryExpression.resolveTypesPartOne(session);
            queryExpression.resolveTypesPartTwo(session);
            rangeTable.prepareTable(session);
            setRangeTableVariables();
        }
    }
