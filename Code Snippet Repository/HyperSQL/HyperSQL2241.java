    public void writeData(int l, Type[] types, Object[] data,
                          HashMappedList cols, int[] primaryKeys) {

        boolean hasPK = primaryKeys != null && primaryKeys.length != 0;
        int     limit = hasPK ? primaryKeys.length
                              : l;

        for (int i = 0; i < limit; i++) {
            int    j = hasPK ? primaryKeys[i]
                             : i;
            Object o = data[j];
            Type   t = types[j];

            if (cols != null) {
                ColumnSchema col = (ColumnSchema) cols.get(j);

                writeFieldPrefix();
                writeString(col.getName().statementName);
            }

            writeData(o, t);
        }
    }
