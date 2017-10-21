    private void resetAccessorKeysCached(Index[] keys) {

        RowStoreAVLHybrid tempStore = new RowStoreAVLHybridExtended(session,
            table, true);

        tempStore.changeToDiskTable(session);

        tempStore.indexList    = indexList;
        tempStore.accessorList = accessorList;

        tempStore.elementCount.set(elementCount.get());

        //
        indexList    = keys;
        accessorList = new CachedObject[indexList.length];

        elementCount.set(0);

        RowIterator iterator = tempStore.rowIterator();

        while (iterator.next()) {
            Row row = iterator.getCurrentRow();
            Row newRow = (Row) getNewCachedObject(session, row.getData(),
                                                  false);

            indexRow(session, newRow);
        }
    }
