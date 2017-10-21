    public synchronized double searchCost(Session session, Index index,
                                          int count, int opType) {

        if (count == 0) {
            return elementCount.get();
        }

        if (opType != OpTypes.EQUAL) {
            return elementCount.get() / 2.0;
        }

        if (index.isUnique() && count == index.getColumnCount()) {
            return 1;
        }

        int position = index.getPosition();

        if (searchCost == null || searchCost.length != indexList.length) {
            searchCost = new double[indexList.length][];
        }

        if (searchCost[position] == null) {
            searchCost[position] = indexList[position].searchCost(session,
                    this);
        }

        return searchCost[index.getPosition()][count - 1];
    }
