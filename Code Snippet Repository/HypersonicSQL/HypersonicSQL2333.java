    public void writeSimple(RowOutputInterface out, ResultMetaData meta) {

        out.writeInt(size);

        for (int i = 0; i < size; i++) {
            Object[] data = table[i];

            out.writeData(meta.getColumnCount(), meta.columnTypes, data, null,
                          null);
        }
    }
