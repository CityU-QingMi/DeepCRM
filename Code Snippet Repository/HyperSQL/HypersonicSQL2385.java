    public void sortFull(Session session) {

        if (reindexTable) {
            store.indexRows(session);
        }

        mainIndex = fullIndex;

        reset();
    }
