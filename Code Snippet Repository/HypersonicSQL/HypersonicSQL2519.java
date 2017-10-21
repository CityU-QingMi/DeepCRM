    public static Object[] getMeta(String name, int type, int defaultValue,
                                   int rangeLow, int rangeHigh) {

        Object[] row = new Object[indexLimit];

        row[indexName]         = name;
        row[indexType]         = ValuePool.getInt(type);
        row[indexClass]        = "Integer";
        row[indexDefaultValue] = ValuePool.getInt(defaultValue);
        row[indexIsRange]      = Boolean.TRUE;
        row[indexRangeLow]     = ValuePool.getInt(rangeLow);
        row[indexRangeHigh]    = ValuePool.getInt(rangeHigh);

        return row;
    }
