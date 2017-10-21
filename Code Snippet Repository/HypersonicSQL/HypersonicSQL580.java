    public boolean setSortIndex(Index index, boolean reversed) {

        if (joinConditions.length == 1) {
            if (joinConditions[0].indexedColumnCount == 0) {
                joinConditions[0].rangeIndex = index;
                joinConditions[0].reversed   = reversed;

                return true;
            }
        }

        return false;
    }
