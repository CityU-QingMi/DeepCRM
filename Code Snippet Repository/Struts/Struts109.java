    private void reloadProviders(List<ContainerProvider> providers) {
        for (ContainerProvider containerProvider : containerProviders) {
            try {
                containerProvider.destroy();
            } catch (Exception e) {
                LOG.warn("error while destroying configuration provider [{}]", containerProvider, e);
            }
        }
        packageProviders = this.configuration.reloadContainer(providers);
    }
