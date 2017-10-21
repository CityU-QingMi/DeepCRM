    public static Object[] getMeta(String name, int type,
                                   boolean defaultValue) {

        Object[] row = new Object[indexLimit];

        row[indexName]         = name;
        row[indexType]         = ValuePool.getInt(type);
        row[indexClass]        = "Boolean";
        row[indexDefaultValue] = defaultValue ? Boolean.TRUE
                                              : Boolean.FALSE;

        return row;
    }
