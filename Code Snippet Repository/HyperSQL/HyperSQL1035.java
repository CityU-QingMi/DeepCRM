    double costFactorColumns(Session session, RangeVariable rangeVar) {

        double cost = 0;

        if (nodes[LEFT].opType == OpTypes.COLUMN
                && nodes[LEFT].getRangeVariable() == rangeVar) {
            if (!nodes[RIGHT].hasReference(rangeVar)) {
                cost = nodes[LEFT].costFactor(session, rangeVar, opType);
            }
        } else if (nodes[RIGHT].opType == OpTypes.COLUMN
                   && nodes[RIGHT].getRangeVariable() == rangeVar) {
            if (!nodes[LEFT].hasReference(rangeVar)) {
                cost = nodes[RIGHT].costFactor(session, rangeVar, opType);
            }
        } else {
            PersistentStore store = rangeVar.rangeTable.getRowStore(session);

            cost = store.elementCount();
        }

        if (cost == 0) {
            PersistentStore store = rangeVar.rangeTable.getRowStore(session);

            cost = store.elementCount();
        }

        if (cost < Index.minimumSelectivity) {
            cost = Index.minimumSelectivity;
        }

        return cost;
    }
