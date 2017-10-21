    public Object[] getData(Long rowId) {

        tempRowData[0] = rowId;

        RowIterator it = idIndex.findFirstRow((Session) session, store,
                                              tempRowData,
                                              idIndex.getDefaultColumnMap());

        it.next();

        return it.getCurrent();
    }
