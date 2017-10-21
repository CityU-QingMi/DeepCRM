    public OrderedHashSet collectAllExpressions(OrderedHashSet set,
            OrderedIntHashSet typeSet, OrderedIntHashSet stopAtTypeSet) {

        if (joinCondition != null) {
            set = joinCondition.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        QueryExpression queryExpression = rangeTable.getQueryExpression();
        Expression      dataExpression  = rangeTable.getDataExpression();

        if (queryExpression != null) {
            set = queryExpression.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        if (dataExpression != null) {
            set = dataExpression.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        return set;
    }
