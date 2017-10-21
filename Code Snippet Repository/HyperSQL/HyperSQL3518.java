    private Result getSingleResult(Session session, int maxRows) {

        int[] limits = sortAndSlice.getLimits(session, this, maxRows);
        Result              r         = buildResult(session, limits);
        RowSetNavigatorData navigator = (RowSetNavigatorData) r.getNavigator();

        if (isDistinctSelect) {
            navigator.removeDuplicates(session);
        }

        if (sortAndSlice.hasOrder()) {
            navigator.sortOrder(session);
        }

        if (limits != SortAndSlice.defaultLimits
                && !sortAndSlice.skipFullResult) {
            navigator.trim(limits[0], limits[1]);
        }

        return r;
    }
