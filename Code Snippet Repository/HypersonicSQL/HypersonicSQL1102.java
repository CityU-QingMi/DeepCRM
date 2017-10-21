    void insertIntoTable(Session session, Result result) {

        PersistentStore store = getRowStore(session);
        RowSetNavigator nav   = result.initialiseNavigator();

        while (nav.next()) {
            Object[] data = nav.getCurrent();
            Object[] newData =
                (Object[]) ArrayUtil.resizeArrayIfDifferent(data, columnCount);

            insertData(session, store, newData);
        }
    }
