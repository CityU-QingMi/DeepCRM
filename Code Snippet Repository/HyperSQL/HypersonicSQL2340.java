    public void intersectAll(Session session, RowSetNavigatorData other) {

        Object[]    compareData = null;
        RowIterator it;
        Object[]    otherData = null;

        sortFull(session);
        other.sortFull(session);

        it = fullIndex.emptyIterator();

        while (next()) {
            Object[] currentData = getCurrent();
            boolean newGroup =
                compareData == null
                || fullIndex.compareRowNonUnique(
                    session, currentData, compareData,
                    visibleColumnCount) != 0;

            if (newGroup) {
                compareData = currentData;
                it          = other.findFirstRow(currentData);
            }

            if (it.next()) {
                otherData = it.getCurrent();

                if (fullIndex.compareRowNonUnique(
                        session, currentData, otherData,
                        visibleColumnCount) == 0) {
                    continue;
                }
            }

            removeCurrent();
        }

        reset();
    }
