    public final void changeToDiskTable(Session session) {

        cache =
            session.sessionData.persistentStoreCollection
                .getSessionDataCache();
        maxMemoryRowCount = Integer.MAX_VALUE;

        if (cache == null) {
            return;
        }

        tableSpace =
            cache.spaceManager.getTableSpace(DataSpaceManager.tableIdDefault);
        isCached = true;

        cache.adjustStoreCount(1);

        if (elementCount.get() == 0) {
            return;
        }

        IndexAVL    idx      = (IndexAVL) indexList[0];
        NodeAVL     root     = (NodeAVL) accessorList[0];
        RowIterator iterator = table.rowIterator(this);

        ArrayUtil.fillArray(accessorList, null);
        ArrayUtil.fillArray(nullsList, false);
        elementCount.set(0);

        while (iterator.next()) {
            Row row = iterator.getCurrentRow();
            Row newRow = (Row) getNewCachedObject(session, row.getData(),
                                                  false);

            indexRow(session, newRow);
        }

        idx.unlinkNodes(this, root);
    }
