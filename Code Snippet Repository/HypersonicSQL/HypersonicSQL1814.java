    public static int[] countSegments(int[] array, int elementCount,
                                      int segments, int interval, int start,
                                      int limit) {

        int[] counts = new int[segments];
        int   index;
        int   element;

        if (interval <= 0) {
            return counts;
        }

        for (int i = 0; i < elementCount; i++) {
            element = array[i];

            if (element < start || element >= limit) {
                continue;
            }

            index = (element - start) / interval;

            counts[index]++;
        }

        return counts;
    }
