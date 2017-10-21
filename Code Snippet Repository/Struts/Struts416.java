    public boolean addResourceStore(final ResourceStore pStore) {
        try {
            final int n = stores.length;
            final ResourceStore[] newStores = new ResourceStore[n + 1];
            System.arraycopy(stores, 0, newStores, 1, n);
            newStores[0] = pStore;
            stores = newStores;
            delegate = new ResourceStoreClassLoader(parent, stores);
            return true;
        } catch (final RuntimeException e) {
            LOG.error("Could not add resource store", e);
        }
        return false;
    }
