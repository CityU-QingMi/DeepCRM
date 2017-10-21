    double costFactorUnaryColumn(Session session, RangeVariable rangeVar) {

        if (nodes[LEFT].opType == OpTypes.COLUMN
                && nodes[LEFT].getRangeVariable() == rangeVar) {
            return nodes[LEFT].costFactor(session, rangeVar, opType);
        } else {
            PersistentStore store = rangeVar.rangeTable.getRowStore(session);
            double          cost  = store.elementCount();

            return cost < Index.minimumSelectivity ? Index.minimumSelectivity
                                                   : cost;
        }
    }
