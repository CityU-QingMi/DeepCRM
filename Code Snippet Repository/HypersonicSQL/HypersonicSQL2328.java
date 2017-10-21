    private void ensureCapacity() {

        if (size == table.length) {
            int        newSize  = size == 0 ? 4
                                            : size * 2;
            Object[][] newTable = new Object[newSize][];

            System.arraycopy(table, 0, newTable, 0, size);

            table = newTable;
        }
    }
