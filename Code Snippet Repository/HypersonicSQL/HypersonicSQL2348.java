    RowIterator findFirstRow(Object[] data) {

        int position = ArraySort.searchFirst(dataTable, 0, size, data, this);

        if (position < 0) {
            position = size;
        } else {
            position--;
        }

        return new DataIterator(position);
    }
