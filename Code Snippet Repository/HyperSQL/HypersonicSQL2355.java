    public void removeCurrent() {

        System.arraycopy(dataTable, currentPos + 1, dataTable, currentPos,
                         size - currentPos - 1);

        dataTable[size - 1] = null;

        currentPos--;
        size--;
    }
