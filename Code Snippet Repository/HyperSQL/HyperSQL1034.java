    protected int compare(int i, boolean full) {

        if (sortOnValues) {
            if (targetSearchValue > values[i]) {
                return 1;
            } else if (targetSearchValue < values[i]) {
                return -1;
            } else if (!full) {
                return 0;
            }
        }

        if (targetSearchValue > keys[i]) {
            return 1;
        } else if (targetSearchValue < keys[i]) {
            return -1;
        }

        return 0;
    }
