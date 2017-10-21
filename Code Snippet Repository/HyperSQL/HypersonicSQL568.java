    public void replaceExpressions(OrderedHashSet expressions,
                                   int resultRangePosition) {

        for (int i = 0; i < indexStartAggregates; i++) {
            exprColumns[i] = exprColumns[i].replaceExpressions(expressions,
                    resultRangePosition);
        }

        if (queryCondition != null) {
            queryCondition = queryCondition.replaceExpressions(expressions,
                    resultRangePosition);
        }

        if (havingCondition != null) {
            havingCondition = havingCondition.replaceExpressions(expressions,
                    resultRangePosition);
        }

        for (int i = 0, len = rangeVariables.length; i < len; i++) {
            rangeVariables[i].replaceExpressions(expressions,
                                                 resultRangePosition);
        }
    }
