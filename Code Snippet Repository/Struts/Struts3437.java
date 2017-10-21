    @Override
    public Map<String, Bundle> getActiveBundles() {
        Map<String, Bundle> bundles = new HashMap<String, Bundle>();
        for (Bundle bundle : felix.getBundleContext().getBundles()) {
            if (bundle.getState() == Bundle.ACTIVE) {
                bundles.put(bundle.getSymbolicName(), bundle);
            }
        }
        return Collections.unmodifiableMap(bundles);
    }
