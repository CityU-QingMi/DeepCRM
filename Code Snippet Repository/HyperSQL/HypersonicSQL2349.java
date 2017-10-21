    private void ensureCapacity() {

        if (size == dataTable.length) {
            int        newSize  = size == 0 ? 4
                                            : size * 2;
            Object[][] newTable = new Object[newSize][];

            System.arraycopy(dataTable, 0, newTable, 0, size);

            dataTable = newTable;
        }
    }
