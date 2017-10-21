    protected Object getColumnValue(int columnIndex) throws SQLException {

        Object[] rowData = getCurrent();
        Object value;

        checkColumn(columnIndex);

        value = rowData[columnIndex - 1];

        trackNull(value);

        return value;
    }
