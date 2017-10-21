    public static Object[] getMeta(String name, int type,
                                   String defaultValue) {

        Object[] row = new Object[indexLimit];

        row[indexName]         = name;
        row[indexType]         = ValuePool.getInt(type);
        row[indexClass]        = "String";
        row[indexDefaultValue] = defaultValue;

        return row;
    }
