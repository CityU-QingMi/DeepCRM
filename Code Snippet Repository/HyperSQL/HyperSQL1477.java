    public RowSetNavigatorClient(RowSetNavigator source, int offset,
                                 int blockSize) {

        this.size          = source.size;
        this.baseBlockSize = blockSize;
        this.currentOffset = offset;
        table              = new Object[blockSize][];

        source.absolute(offset);

        for (int count = 0; count < blockSize; count++) {
            table[count] = source.getCurrent();

            source.next();
        }

        source.beforeFirst();
    }
