    OrderedHashSet collectRangeVariables(OrderedHashSet set) {

        set = leftQueryExpression.collectRangeVariables(set);

        if (rightQueryExpression != null) {
            set = rightQueryExpression.collectRangeVariables(set);
        }

        return set;
    }
