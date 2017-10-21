    public final void indexRows(Session session) {

        writeLock();

        try {
            for (int i = 1; i < indexList.length; i++) {
                setAccessor(indexList[i], null);
            }

            RowIterator it = rowIterator();

            while (it.next()) {
                Row row = it.getCurrentRow();

                ((RowAVL) row).clearNonPrimaryNodes();

                for (int i = 1; i < indexList.length; i++) {
                    indexList[i].insert(session, this, row);
                }
            }
        } finally {
            writeUnlock();
        }
    }
