    public void prepareMultiColumn(int count) {

        sortOrder      = new int[count];
        sortDescending = new boolean[count];
        sortNullsLast  = new boolean[count];

        for (int i = 0; i < count; i++) {
            sortOrder[i] = i;
        }
    }
