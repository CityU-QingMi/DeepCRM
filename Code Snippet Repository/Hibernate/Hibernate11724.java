    private void cleanUp() {
        for (Iterator it = factories.iterator(); it.hasNext(); ) {
            try {
                ((RegionFactory) it.next()).stop();
            }
            catch (Exception e) {
                storeException(e);
            }
            finally {
                it.remove();
            }
        }
        factories.clear();

        for (Iterator it = caches.iterator(); it.hasNext(); ) {
            try {
                Cache cache = (Cache) it.next();
                cache.stop();
            }
            catch (Exception e) {
                storeException(e);
            }
            finally {
                it.remove();
            }
        }
        caches.clear();
    }
