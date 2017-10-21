    Row getRow(Session session, Object[] args) {

        int             rowIdIndex = result.metaData.getColumnCount();
        Long            rowId      = (Long) args[rowIdIndex];
        PersistentStore store      = baseTable.getRowStore(session);
        Row             row        = null;

        if (rowIdIndex + 2 == result.metaData.getExtendedColumnCount()) {
            Object[] data =
                ((RowSetNavigatorData) result.getNavigator()).getData(
                    rowId.longValue());

            if (data != null) {
                row = (Row) data[rowIdIndex + 1];
            }
        } else {
            int id = (int) rowId.longValue();

            row = (Row) store.get(id, false);
        }

        this.result = null;

        return row;
    }
