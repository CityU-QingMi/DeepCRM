    OrderedHashSet collectRangeVariables(RangeVariable[] rangeVars,
                                         OrderedHashSet set) {

        set = leftQueryExpression.collectRangeVariables(rangeVars, set);

        if (rightQueryExpression != null) {
            set = rightQueryExpression.collectRangeVariables(rangeVars, set);
        }

        return set;
    }
