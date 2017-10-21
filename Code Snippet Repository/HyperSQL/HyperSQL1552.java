    private CachedObject getNewInstance() {

        try {
            CachedObject object =
                (CachedObject) constructor.newInstance(new Object[]{
                    Integer.valueOf(blockSize) });

            return object;
        } catch (Exception e) {
            return null;
        }
    }
