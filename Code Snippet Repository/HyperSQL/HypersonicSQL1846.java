    public static boolean hasNull(Object[] array, int[] columnMap) {

        int count = columnMap.length;

        for (int i = 0; i < count; i++) {
            if (array[columnMap[i]] == null) {
                return true;
            }
        }

        return false;
    }
