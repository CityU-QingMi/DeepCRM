    protected void onBundleStopped(Bundle bundle) {
        Set<String> packages = bundleAccessor.getPackagesByBundle(bundle);
        if (!packages.isEmpty()) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("The bundle [{}] has been stopped. The packages [{}] will be disabled", bundle.getSymbolicName(), StringUtils.join(packages, ","));
            }
            for (String packageName : packages) {
                configuration.removePackageConfig(packageName);
            }
        }
    }
