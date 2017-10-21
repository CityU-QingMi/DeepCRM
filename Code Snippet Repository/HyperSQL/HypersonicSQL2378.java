    public void removeDuplicates(Session session) {

        sortFull(session);

        Object[] lastRowData = null;

        while (next()) {
            Object[] currentData = getCurrent();

            if (lastRowData != null
                    && fullIndex.compareRow(session, lastRowData, currentData)
                       == 0) {
                removeCurrent();
            } else {
                lastRowData = currentData;
            }
        }

        reset();
    }
