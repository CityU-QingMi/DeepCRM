    int[] getColumnIndexes(HashMappedList list) {

        int[] cols = new int[list.size()];

        for (int i = 0; i < cols.length; i++) {
            cols[i] = ((Integer) list.get(i)).intValue();
        }

        return cols;
    }
