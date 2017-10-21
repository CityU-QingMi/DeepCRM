    public void bundleChanged(BundleEvent bundleEvent) {
        Bundle bundle = bundleEvent.getBundle();
        String bundleName = bundle.getSymbolicName();
        if (bundleName != null && shouldProcessBundle(bundle)) {
            switch (bundleEvent.getType()) {
                case BundleEvent.STARTED:
                    LOG.trace("The bundle [{}] has been activated and will be scanned for struts configuration", bundleName);
                    loadConfigFromBundle(bundle);
                    break;
                case BundleEvent.STOPPED:
                    onBundleStopped(bundle);
                    break;
            }
        }
    }
