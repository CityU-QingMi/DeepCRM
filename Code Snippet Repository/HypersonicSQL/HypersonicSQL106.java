    boolean hasReference(RangeVariable[] ranges, int exclude) {

        OrderedHashSet set = collectRangeVariables(ranges, null);

        if (set == null) {
            return false;
        }

        for (int j = 0; j < set.size(); j++) {
            if (set.get(j) != ranges[exclude]) {
                return true;
            }
        }

        return false;
    }
