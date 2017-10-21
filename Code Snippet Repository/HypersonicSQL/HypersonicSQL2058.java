    protected int compare(int i) {

        if (targetSearchValue > keys[i]) {
            return 1;
        } else if (targetSearchValue < keys[i]) {
            return -1;
        }

        return 0;
    }
