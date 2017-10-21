    private void updateAccessCounts() {

        CachedObject r;
        int          count;

        if (updateAccess) {
            for (int i = 0; i < objectKeyTable.length; i++) {
                r = (CachedObject) objectKeyTable[i];

                if (r != null) {
                    count = r.getAccessCount();

                    if (count > accessTable[i]) {
                        accessTable[i] = count;
                    }
                }
            }
        }
    }
