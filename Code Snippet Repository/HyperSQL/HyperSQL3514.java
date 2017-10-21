    OrderedHashSet collectRangeVariables(RangeVariable[] rangeVars,
                                         OrderedHashSet set) {

        for (int i = 0; i < indexStartAggregates; i++) {
            set = exprColumns[i].collectRangeVariables(rangeVars, set);
        }

        if (queryCondition != null) {
            set = queryCondition.collectRangeVariables(rangeVars, set);
        }

        if (havingCondition != null) {
            set = havingCondition.collectRangeVariables(rangeVars, set);
        }

        return set;
    }
