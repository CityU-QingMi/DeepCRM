    private int binaryFirstSearch() {

        int low     = 0;
        int high    = count;
        int mid     = 0;
        int compare = 0;
        int found   = count;

        while (low < high) {
            mid     = (low + high) >>> 1;
            compare = compare(mid);

            if (compare < 0) {
                high = mid;
            } else if (compare > 0) {
                low = mid + 1;
            } else {
                high  = mid;
                found = mid;
            }
        }

        return found == count ? -1
                              : found;
    }
