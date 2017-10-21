    void getBlock(int offset) {

        try {
            RowSetNavigatorClient source = session.getRows(id, offset,
                baseBlockSize);

            table         = source.table;
            currentOffset = source.currentOffset;
        } catch (HsqlException e) {}
    }
