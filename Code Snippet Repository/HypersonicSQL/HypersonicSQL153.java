    void setAutoAttributesAsColumn(RangeVariable range, int i) {

        columnIndex   = i;
        column        = range.getColumn(i);
        dataType      = column.getDataType();
        columnName    = range.getColumnAlias(i).name;
        tableName     = range.getTableAlias().name;
        rangeVariable = range;

        rangeVariable.addColumn(columnIndex);
    }
