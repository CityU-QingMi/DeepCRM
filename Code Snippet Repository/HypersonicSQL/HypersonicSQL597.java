    public void replaceExpressions(OrderedHashSet expressions,
                                   int resultRangePosition) {

        QueryExpression queryExpression = rangeTable.getQueryExpression();
        Expression      dataExpression  = rangeTable.getDataExpression();

        if (dataExpression != null) {
            dataExpression = dataExpression.replaceExpressions(expressions,
                    resultRangePosition);
        }

        if (queryExpression != null) {
            queryExpression.replaceExpressions(expressions,
                                               resultRangePosition);
        }

        if (joinCondition != null) {
            joinCondition = joinCondition.replaceExpressions(expressions,
                    resultRangePosition);
        }

        for (int i = 0; i < joinConditions.length; i++) {
            joinConditions[i].replaceExpressions(expressions,
                                                 resultRangePosition);
        }

        for (int i = 0; i < whereConditions.length; i++) {
            whereConditions[i].replaceExpressions(expressions,
                                                  resultRangePosition);
        }
    }
