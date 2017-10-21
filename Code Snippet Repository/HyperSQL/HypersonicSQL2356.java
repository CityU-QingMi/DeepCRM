    public void union(Session session, RowSetNavigatorData other) {

        Object[] currentData;

        removeDuplicates(session);
        other.removeDuplicates(session);

        mainIndex = fullIndex;

        while (other.next()) {
            currentData = other.getCurrent();

            int position = ArraySort.searchFirst(dataTable, 0, size,
                                                 currentData, this);

            if (position < 0) {
                position   = -position - 1;
                currentPos = position;

                insert(currentData);
            }
        }

        reset();
    }
