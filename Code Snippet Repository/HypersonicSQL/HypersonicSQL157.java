    OrderedHashSet collectRangeVariables(OrderedHashSet set) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                set = nodes[i].collectRangeVariables(set);
            }
        }

        if (rangeVariable != null) {
            if (set == null) {
                set = new OrderedHashSet();
            }

            set.add(rangeVariable);
        }

        return set;
    }
