    public synchronized void resetAccessorKeys(Session session, Index[] keys) {

        resettingAccessor = true;
        try {
            if (indexList.length == 0 || accessorList[0] == null) {
                indexList = keys;
                accessorList = new CachedObject[indexList.length];

                return;
            }

            boolean result;

            if (isCached) {
                resetAccessorKeysCached(keys);
            }
            else {
                super.resetAccessorKeys(session, keys);
            }
        } finally {
            resettingAccessor = false;
        }

    }
