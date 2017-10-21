    CachedObject release(long pos) {

        CachedObject r = (CachedObject) addOrRemoveObject(null, pos, true);

        if (r == null) {
            return null;
        }

        cacheBytesLength -= r.getStorageSize();

        r.setInMemory(false);

        return r;
    }
