    public void reindex(Session session, Index index) {

        writeLock();

        try {
            setAccessor(index, null);

            RowIterator it = table.rowIterator(this);

            while (it.next()) {
                RowAVL row = (RowAVL) it.getCurrentRow();

                row.getNode(index.getPosition()).delete();
                index.insert(session, this, row);
            }
        } finally {
            writeUnlock();
        }
    }
