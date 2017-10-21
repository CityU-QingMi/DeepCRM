    public boolean hasUniqueNotNullRows(Session session) {

        sortFull(session);
        reset();

        Object[] lastRowData = null;

        while (next()) {
            Object[] currentData = getCurrent();

            if (hasNull(currentData)) {
                continue;
            }

            if (lastRowData != null
                    && fullIndex.compareRow(session, lastRowData, currentData)
                       == 0) {
                return false;
            } else {
                lastRowData = currentData;
            }
        }

        return true;
    }
