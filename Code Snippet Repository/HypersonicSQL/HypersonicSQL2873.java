    public static ResultMetaData newResultMetaData(Type[] types,
            int[] baseColumnIndexes, int colCount, int extColCount) {

        ResultMetaData md = new ResultMetaData(RESULT_METADATA);

        md.columnLabels        = new String[colCount];
        md.columns             = new ColumnBase[colCount];
        md.columnTypes         = types;
        md.colIndexes          = baseColumnIndexes;
        md.columnCount         = colCount;
        md.extendedColumnCount = extColCount;

        return md;
    }
