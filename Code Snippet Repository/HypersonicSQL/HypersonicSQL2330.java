    public void removeCurrent() {

        System.arraycopy(table, currentPos + 1, table, currentPos,
                         size - currentPos - 1);

        table[size - 1] = null;

        currentPos--;
        size--;
    }
