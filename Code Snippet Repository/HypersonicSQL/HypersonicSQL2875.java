    public static ResultMetaData newGeneratedColumnsMetaData(
            int[] columnIndexes, String[] columnNames) {

        if (columnIndexes != null) {
            ResultMetaData md = new ResultMetaData(GENERATED_INDEX_METADATA);

            md.columnCount         = columnIndexes.length;
            md.extendedColumnCount = columnIndexes.length;
            md.colIndexes          = new int[columnIndexes.length];

            for (int i = 0; i < columnIndexes.length; i++) {
                md.colIndexes[i] = columnIndexes[i] - 1;
            }

            return md;
        } else if (columnNames != null) {
            ResultMetaData md = new ResultMetaData(GENERATED_NAME_METADATA);

            md.columnLabels        = new String[columnNames.length];
            md.columnCount         = columnNames.length;
            md.extendedColumnCount = columnNames.length;
            md.columnLabels        = columnNames;

            return md;
        } else {
            return null;
        }
    }
