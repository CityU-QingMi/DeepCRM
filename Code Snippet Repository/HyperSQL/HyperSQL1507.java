    void insert(Object[] data) {

        ensureCapacity();
        System.arraycopy(dataTable, currentPos, dataTable, currentPos + 1,
                         size - currentPos);

        dataTable[currentPos] = data;

        size++;
    }
