    int[] getColumnIndexes(OrderedHashSet set) {

        int[] cols = new int[set.size()];

        for (int i = 0; i < cols.length; i++) {
            cols[i] = getColumnIndex((String) set.get(i));

            if (cols[i] == -1) {
                throw Error.error(ErrorCode.X_42501, (String) set.get(i));
            }
        }

        return cols;
    }
