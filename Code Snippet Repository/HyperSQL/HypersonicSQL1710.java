    public String getColumnName(int column) throws SQLException {

        checkColumn(column--);

        if (useColumnName) {
            String name = resultMetaData.columns[column].getNameString();

            if (name != null && name.length() > 0) {
                return name;
            }
        }

        String label = resultMetaData.columnLabels[column];

        return label == null ? resultMetaData.columns[column].getNameString()
                             : label;
    }
