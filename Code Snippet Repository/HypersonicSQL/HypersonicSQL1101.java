    Row insertSingleRow(Session session, PersistentStore store, Object[] data,
                        int[] changedCols) {

        generateAndCheckData(session, data);

        if (isView) {

            // may have domain column
            return null;
        }

        Row row = (Row) store.getNewCachedObject(session, data, true);

        session.addInsertAction(this, store, row, changedCols);

        return row;
    }
