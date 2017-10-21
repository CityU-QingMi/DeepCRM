    static boolean containsRights(boolean isFull, OrderedHashSet columnSet,
                                  OrderedHashSet otherColumnSet,
                                  boolean otherIsFull) {

        if (isFull) {
            return true;
        }

        if (otherIsFull) {
            return false;
        }

        if (otherColumnSet != null
                && (columnSet == null
                    || !columnSet.containsAll(otherColumnSet))) {
            return false;
        }

        return true;
    }
