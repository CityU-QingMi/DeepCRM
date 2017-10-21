    RowIterator findFkRef(Session session, Object[] row) {

        if (row == null || ArrayUtil.hasNull(row, core.mainCols)) {
            return core.refIndex.emptyIterator();
        }

        PersistentStore store = core.refTable.getRowStore(session);

        return core.refIndex.findFirstRow(session, store, row, core.mainCols);
    }
