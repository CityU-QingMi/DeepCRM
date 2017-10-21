    private boolean needReloadPackageProviders() {
        if (packageProviders != null) {
            for (PackageProvider provider : packageProviders) {
                if (provider.needsReload()) {
                    LOG.info("Detected package provider [{}] needs to be reloaded. Reloading all providers.", provider);
                    return true;
                }
            }
        }
        return false;
    }
