    public void removeDuplicates(Session session) {

        sortFull(session);
        reset();

        int      lastRowPos  = -1;
        Object[] lastRowData = null;

        while (next()) {
            Object[] currentData = getCurrent();

            if (lastRowData == null) {
                lastRowPos  = currentPos;
                lastRowData = currentData;

                continue;
            }

            if (fullIndex.compareRow(session, lastRowData, currentData) != 0) {
                lastRowPos++;

                lastRowData           = currentData;
                dataTable[lastRowPos] = currentData;
            }
        }

        for (int i = lastRowPos + 1; i < size; i++) {
            dataTable[i] = null;
        }

        super.size = lastRowPos + 1;

        reset();
    }
