    public ResultMetaData getNewMetaData(int[] columnMap) {

        ResultMetaData newMeta = newResultMetaData(columnMap.length);

        ArrayUtil.projectRow(columnLabels, columnMap, newMeta.columnLabels);
        ArrayUtil.projectRow(columnTypes, columnMap, newMeta.columnTypes);
        ArrayUtil.projectRow(columns, columnMap, newMeta.columns);

        return newMeta;
    }
