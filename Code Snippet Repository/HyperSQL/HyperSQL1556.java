    private synchronized void saveRows(int count) {

        if (count == 0) {
            return;
        }

        rowComparator.setType(CachedObjectComparator.COMPARE_POSITION);
        ArraySort.sort(rowTable, 0, count, rowComparator);
        dataFileCache.saveRows(rowTable, 0, count);

        saveRowCount += count;
    }
