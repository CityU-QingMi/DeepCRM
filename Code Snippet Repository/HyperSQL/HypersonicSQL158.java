    OrderedHashSet collectRangeVariables(RangeVariable[] rangeVariables,
                                         OrderedHashSet set) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                set = nodes[i].collectRangeVariables(rangeVariables, set);
            }
        }

        if (rangeVariable != null) {
            for (int i = 0; i < rangeVariables.length; i++) {
                if (rangeVariables[i] == rangeVariable) {
                    if (set == null) {
                        set = new OrderedHashSet();
                    }

                    set.add(rangeVariable);

                    break;
                }
            }
        }

        return set;
    }
