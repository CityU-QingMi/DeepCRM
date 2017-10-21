    public boolean removeResourceStore(final ResourceStore pStore) {

        final int n = stores.length;
        int i = 0;

        // FIXME: this should be improved with a Map
        // find the pStore and index position with var i
        while ((i < n) && (stores[i] != pStore)) {
            i++;
        }

        // pStore was not found
        if (i == n) {
            return false;
        }

        // if stores length > 1 then array copy old values, else create new empty store
        final ResourceStore[] newStores = new ResourceStore[n - 1];
        if (i > 0) {
            System.arraycopy(stores, 0, newStores, 0, i);
        }
        if (i < n - 1) {
            System.arraycopy(stores, i + 1, newStores, i, (n - i - 1));
        }

        stores = newStores;
        delegate = new ResourceStoreClassLoader(parent, stores);
        return true;
    }
