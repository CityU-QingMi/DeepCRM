    public void union(Session session, RowSetNavigatorData other) {

        Object[] currentData;
        int      colCount = table.getColumnTypes().length;

        removeDuplicates(session);
        other.reset();

        while (other.next()) {
            currentData = other.getCurrent();

            RowIterator it = findFirstRow(currentData);

            if (!it.next()) {
                currentData =
                    (Object[]) ArrayUtil.resizeArrayIfDifferent(currentData,
                        colCount);

                add(currentData);
            }

            it.release();
        }

        other.release();
        reset();
    }
