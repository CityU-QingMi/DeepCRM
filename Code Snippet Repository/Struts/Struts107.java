    public synchronized void conditionalReload() {
        if (reloadConfigs || providersChanged) {
            LOG.debug("Checking ConfigurationProviders for reload.");
            List<ContainerProvider> providers = getContainerProviders();
            boolean reload = needReloadContainerProviders(providers);
            if (!reload) {
                reload = needReloadPackageProviders();
            }
            if (reload) {
                reloadProviders(providers);
            }
            updateReloadConfigsFlag();
            providersChanged = false;
        }
    }
