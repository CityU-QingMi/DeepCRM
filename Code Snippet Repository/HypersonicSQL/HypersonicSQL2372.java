    public void intersect(Session session, RowSetNavigatorData other) {

        removeDuplicates(session);
        other.sortFull(session);

        while (next()) {
            Object[] currentData = getCurrent();
            boolean  hasRow      = other.containsRow(currentData);

            if (!hasRow) {
                removeCurrent();
            }
        }

        other.release();
    }
