    protected void swap(int i1, int i2) {

        int col1 = keys[i1];
        int col2 = values[i1];

        keys[i1]   = keys[i2];
        values[i1] = values[i2];
        keys[i2]   = col1;
        values[i2] = col2;
    }
