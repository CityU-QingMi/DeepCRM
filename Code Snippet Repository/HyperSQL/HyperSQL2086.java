    public void prepareData() {

        if (columns != null) {
            for (int i = 0; i < columnCount; i++) {
                if (columnTypes[i] == null) {
                    columnTypes[i] = columns[i].getDataType();
                }
            }
        }
    }
