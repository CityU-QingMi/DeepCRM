    public void read(RowInputInterface in, ResultMetaData meta) {

        id            = in.readLong();
        size          = in.readInt();
        currentOffset = in.readInt();
        baseBlockSize = in.readInt();

        if (table.length < baseBlockSize) {
            table = new Object[baseBlockSize][];
        }

        for (int i = 0; i < baseBlockSize; i++) {
            table[i] = in.readData(meta.columnTypes);
        }
    }
