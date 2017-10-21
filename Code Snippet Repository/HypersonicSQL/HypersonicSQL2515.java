    public static Object[] getMeta(String name, int type) {

        Object[] row = new Object[indexLimit];

        row[indexName]         = name;
        row[indexType]         = ValuePool.getInt(type);
        row[indexClass]        = "Long";
        row[indexDefaultValue] = Long.valueOf(0);

        return row;
    }
