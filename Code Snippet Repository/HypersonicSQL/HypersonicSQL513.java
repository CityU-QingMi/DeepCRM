    public OrderedHashSet collectAllExpressions(OrderedHashSet set,
            OrderedIntHashSet typeSet, OrderedIntHashSet stopAtTypeSet) {

        set = leftQueryExpression.collectAllExpressions(set, typeSet,
                stopAtTypeSet);

        if (rightQueryExpression != null) {
            set = rightQueryExpression.collectAllExpressions(set, typeSet,
                    stopAtTypeSet);
        }

        return set;
    }
