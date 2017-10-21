    public void replaceColumnReferences(RangeVariable range,
                                        Expression[] list) {

        for (int i = 0; i < indexStartAggregates; i++) {
            exprColumns[i] = exprColumns[i].replaceColumnReferences(range,
                    list);
        }

        if (queryCondition != null) {
            queryCondition = queryCondition.replaceColumnReferences(range,
                    list);
        }

        if (havingCondition != null) {
            havingCondition = havingCondition.replaceColumnReferences(range,
                    list);
        }

        for (int i = 0, len = rangeVariables.length; i < len; i++) {
            rangeVariables[i].replaceColumnReferences(range, list);
        }
    }
