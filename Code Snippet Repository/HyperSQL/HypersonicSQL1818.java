    public static int searchFirst(Object[] array, int start, int limit,
                                  Object value, Comparator c) {

        int low   = start;
        int high  = limit;
        int found = limit;
        int mid;
        int compare;

        while (low < high) {
            mid     = (low + high) >>> 1;
            compare = c.compare(value, array[mid]);

            if (compare < 0) {
                high = mid;
            } else if (compare > 0) {
                low = mid + 1;
            } else {
                high  = mid;
                found = mid;
            }
        }

        return found == limit ? -low - 1
                              : found;
    }
