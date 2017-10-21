    long getStorageSizeEstimate() {

        if (elementCount.get() == 0) {
            return 0;
        }

        CachedObject accessor = getAccessor(indexList[0]);
        CachedObject row      = get(accessor.getPos());

        return row.getStorageSize() * elementCount.get();
    }
