    public static boolean isInSortedArray(char ch, char[] array) {

        if (array.length == 0 || ch < array[0]
                || ch > array[array.length - 1]) {
            return false;
        }

        int low  = 0;
        int high = array.length;
        int mid  = 0;

        while (low < high) {
            mid = (low + high) >>> 1;

            if (ch < array[mid]) {
                high = mid;
            } else if (ch > array[mid]) {
                low = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }
