    public void add(Object[] data) {

        try {
            Row row = (Row) store.getNewCachedObject((Session) session, data,
                false);

            store.indexRow((Session) session, row);

            size++;
        } catch (HsqlException e) {}
    }
