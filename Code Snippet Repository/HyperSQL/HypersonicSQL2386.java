    public void sortOrder(Session session) {

        if (orderIndex != null) {
            if (reindexTable) {
                store.indexRows(session);
            }

            mainIndex = orderIndex;

            if (iterator != null) {
                iterator.release();
            }

            reset();
        }
    }
