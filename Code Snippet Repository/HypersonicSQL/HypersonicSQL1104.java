    void insertResult(Session session, PersistentStore store, Result ins) {

        RowSetNavigator nav = ins.initialiseNavigator();

        while (nav.next()) {
            Object[] data = nav.getCurrent();
            Object[] newData =
                (Object[]) ArrayUtil.resizeArrayIfDifferent(data, columnCount);

            insertData(session, store, newData);
        }
    }
