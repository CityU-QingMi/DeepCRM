    public static boolean hasAllNull(Object[] array, int[] columnMap) {

        int count = columnMap.length;

        for (int i = 0; i < count; i++) {
            if (array[columnMap[i]] != null) {
                return false;
            }
        }

        return true;
    }
