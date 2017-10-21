    public static Object[] getMeta(String name, int type, int defaultValue,
                                   int[] values) {

        Object[] row = new Object[indexLimit];

        row[indexName]         = name;
        row[indexType]         = ValuePool.getInt(type);
        row[indexClass]        = "Integer";
        row[indexDefaultValue] = ValuePool.getInt(defaultValue);
        row[indexValues]       = values;

        return row;
    }
