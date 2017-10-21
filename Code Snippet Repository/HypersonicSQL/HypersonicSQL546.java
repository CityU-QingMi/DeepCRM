    OrderedHashSet collectRangeVariables(OrderedHashSet set) {

        for (int i = 0; i < indexStartAggregates; i++) {
            set = exprColumns[i].collectRangeVariables(set);
        }

        if (queryCondition != null) {
            set = queryCondition.collectRangeVariables(set);
        }

        if (havingCondition != null) {
            set = havingCondition.collectRangeVariables(set);
        }

        return set;
    }
