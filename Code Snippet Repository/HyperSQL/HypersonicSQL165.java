    double costFactor(Session session, RangeVariable range, int operation) {

        if (range.rangeTable instanceof TableDerived) {
            return 1024;
        }

        PersistentStore store = range.rangeTable.getRowStore(session);
        int indexType = range.rangeTable.indexTypeForColumn(session,
            columnIndex);
        double factor;

        switch (indexType) {

            case Index.INDEX_UNIQUE :
                if (operation == OpTypes.EQUAL) {
                    factor = 1;
                } else {
                    factor = store.elementCount() / 2.0;
                }
                break;

            case Index.INDEX_NON_UNIQUE :
                if (operation == OpTypes.EQUAL) {
                    factor = store.elementCount() / 8.0;

                    if (factor > 1024) {
                        factor = 1024;
                    }
                } else {
                    factor = store.elementCount() / 2.0;
                }
                break;

            case Index.INDEX_NONE :
            default :
                factor = store.elementCount();
                break;
        }

        return factor < Index.minimumSelectivity ? Index.minimumSelectivity
                                                 : factor;
    }
