    public OrderedHashSet collectAllExpressions(OrderedHashSet set,
            OrderedIntHashSet typeSet, OrderedIntHashSet stopAtTypeSet) {

        for (int i = 0; i < indexStartAggregates; i++) {
            set = exprColumns[i].collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        if (queryCondition != null) {
            set = queryCondition.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        if (havingCondition != null) {
            set = havingCondition.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        for (int i = 0; i < rangeVariables.length; i++) {
            rangeVariables[i].collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        return set;
    }
