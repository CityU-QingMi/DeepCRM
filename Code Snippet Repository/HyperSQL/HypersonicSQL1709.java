    public String getColumnLabel(int column) throws SQLException {

        checkColumn(column--);

        String label = resultMetaData.columnLabels[column];

        if (label != null && label.length() > 0) {
            return label;
        }

        return resultMetaData.columns[column].getNameString();
    }
