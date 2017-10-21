    protected void moveAndInsertRow(int i, int j) {

        int col1 = keys[i];
        int col2 = values[i];

        moveRows(j, j + 1, i - j);

        keys[j]   = col1;
        values[j] = col2;
    }
