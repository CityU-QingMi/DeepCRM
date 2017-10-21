    private void updateObjectAccessCounts() {

        CachedObject r;
        int          count;

        if (updateAccess) {
            for (int i = 0; i < objectKeyTable.length; i++) {
                r = (CachedObject) objectKeyTable[i];

                if (r != null) {
                    count = accessTable[i];

                    r.updateAccessCount(count);
                }
            }
        }
    }
