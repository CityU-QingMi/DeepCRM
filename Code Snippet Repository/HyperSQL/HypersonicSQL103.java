    OrderedHashSet collectRangeVariables(RangeVariable[] rangeVariables,
                                         OrderedHashSet set) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                set = nodes[i].collectRangeVariables(rangeVariables, set);
            }
        }

        if (table != null && table.queryExpression != null) {
            set = table.queryExpression.collectRangeVariables(rangeVariables,
                    set);
        }

        return set;
    }
