    private double searchCost(Session session, Table table, Index index,
                              int count, int opType) {

        if (table instanceof TableDerived) {
            return 1000;
        } else {
            return table.getRowStore(session).searchCost(session, index,
                                     count, opType);
        }
    }
