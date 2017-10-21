    public void write(RowOutputInterface out, ResultMetaData meta) {

        int limit = size - currentOffset;

        if (limit > table.length) {
            limit = table.length;
        }

        out.writeLong(id);
        out.writeInt(size);
        out.writeInt(currentOffset);
        out.writeInt(limit);

        for (int i = 0; i < limit; i++) {
            Object[] data = table[i];

            out.writeData(meta.getColumnCount(), meta.columnTypes, data, null,
                          null);
        }
    }
