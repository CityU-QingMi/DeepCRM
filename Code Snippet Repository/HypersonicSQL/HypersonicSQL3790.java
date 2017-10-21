    synchronized TypedComparator getComparator(Session session) {

        if (comparator == null) {
            TypedComparator c    = Type.newComparator(session);
            SortAndSlice    sort = new SortAndSlice();

            sort.prepareMultiColumn(dataTypes.length);
            c.setType(this, sort);

            comparator = c;
        }

        return comparator;
    }
