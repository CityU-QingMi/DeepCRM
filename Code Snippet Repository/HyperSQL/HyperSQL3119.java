    public int compare(Session session, Object a, Object b,
                       SortAndSlice sort) {

        if (a == b) {
            return 0;
        }

        if (a == null) {
            return sort.sortNullsLast[0] ? 1
                                         : -1;
        }

        if (b == null) {
            return sort.sortNullsLast[0] ? -1
                                         : 1;
        }

        int result = compare(session, a, b);

        return sort.sortDescending[0] ? -result
                                      : result;
    }
