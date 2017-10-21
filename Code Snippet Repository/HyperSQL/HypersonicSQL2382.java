    boolean containsRow(Object[] data) {

        RowIterator it = mainIndex.findFirstRow((Session) session, store,
            data);
        boolean result = it.next();

        it.release();

        return result;
    }
