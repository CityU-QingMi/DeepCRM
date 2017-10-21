    protected boolean reset() {

        if (maxCapacity == 0 || maxCapacity > threshold) {
            rehash(hashIndex.linkTable.length * 2);

            return true;
        } else if (purgePolicy == PURGE_ALL) {
            clear();

            return true;
        } else if (purgePolicy == PURGE_QUARTER) {
            clear(threshold / 4, threshold >> 8);

            return true;
        } else if (purgePolicy == PURGE_HALF) {
            clear(threshold / 2, threshold >> 8);

            return true;
        } else if (purgePolicy == NO_PURGE) {
            return false;
        }

        return false;
    }
