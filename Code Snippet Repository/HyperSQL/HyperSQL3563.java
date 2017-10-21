    RowIterator findUniqueRows(Session session, Object[] row) {

        if (row == null || ArrayUtil.hasNull(row, core.mainCols)) {
            return core.mainIndex.emptyIterator();
        }

        PersistentStore store = core.mainTable.getRowStore(session);

        return core.mainIndex.findFirstRow(session, store, row, core.mainCols);
    }
