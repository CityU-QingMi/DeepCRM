    int[] findColumnIndexes(String[] list) {

        int[] cols = new int[list.length];

        for (int i = 0; i < cols.length; i++) {
            cols[i] = findColumn(list[i]);
        }

        return cols;
    }
