    protected void clear() {

        Iterator it = cache.getIterator();

        while (it.hasNext()) {
            CachedObject row = (CachedObject) it.next();

            row.setInMemory(false);
            row.destroy();
        }

        cache.clear();

        fileStartFreePosition = fileFreePosition = initialFreePos;

        initBuffers();
    }
