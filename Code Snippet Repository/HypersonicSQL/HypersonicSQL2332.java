    public void readSimple(RowInputInterface in, ResultMetaData meta) {

        size = in.readInt();

        if (table.length < size) {
            table = new Object[size][];
        }

        for (int i = 0; i < size; i++) {
            table[i] = in.readData(meta.columnTypes);
        }
    }
