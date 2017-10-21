    private void insertTableValues(Session session, PersistentStore store) {

        Result          result = nodes[LEFT].getResult(session);
        RowSetNavigator nav    = result.navigator;

        while (nav.next()) {
            Object[] data    = nav.getCurrent();
            Object[] newdata = (Object[]) ArrayUtil.duplicateArray(data);
            Row row = (Row) store.getNewCachedObject(session, newdata, false);

            try {
                store.indexRow(session, row);
            } catch (HsqlException e) {}
        }
    }
