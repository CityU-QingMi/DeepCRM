    public void replaceColumnReferences(RangeVariable range,
                                        Expression[] list) {

        QueryExpression queryExpression = rangeTable.getQueryExpression();
        Expression      dataExpression  = rangeTable.getDataExpression();

        if (dataExpression != null) {
            dataExpression = dataExpression.replaceColumnReferences(range,
                    list);
        }

        if (queryExpression != null) {
            queryExpression.replaceColumnReferences(range, list);
        }

        if (joinCondition != null) {
            joinCondition = joinCondition.replaceColumnReferences(range, list);
        }

        for (int i = 0; i < joinConditions.length; i++) {
            joinConditions[i].replaceColumnReferences(range, list);
        }

        for (int i = 0; i < whereConditions.length; i++) {
            whereConditions[i].replaceColumnReferences(range, list);
        }
    }
