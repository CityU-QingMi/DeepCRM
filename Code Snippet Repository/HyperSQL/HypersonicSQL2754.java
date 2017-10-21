    public void release() {

        if (!isCached) {
            destroy();
        }

        if (isCached) {
            cache.adjustStoreCount(-1);

            cache    = null;
            isCached = false;
        }

        elementCount.set(0);
        ArrayUtil.fillArray(accessorList, null);
    }
