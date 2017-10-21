    public long getNodeCount(Session session, PersistentStore store) {

        long        count = 0;
        RowIterator it    = firstRow(session, store, 0, null);

        while (it.next()) {
            count++;
        }

        return count;
    }
