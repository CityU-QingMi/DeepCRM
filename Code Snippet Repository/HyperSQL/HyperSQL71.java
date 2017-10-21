    boolean[] getColumnCheckList(int[] columnIndexes) {

        boolean[] columnCheckList = new boolean[columnCount];

        for (int i = 0; i < columnIndexes.length; i++) {
            int index = columnIndexes[i];

            if (index > -1) {
                columnCheckList[index] = true;
            }
        }

        return columnCheckList;
    }
