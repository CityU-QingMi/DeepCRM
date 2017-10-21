    public void buildActionConfigs() {
        //setup reload class loader based on dev settings
        initReloadClassLoader();

        if (!disableActionScanning) {
            if (actionPackages == null && packageLocators == null) {
                throw new ConfigurationException("At least a list of action packages or action package locators " +
                        "must be given using one of the properties [struts.convention.action.packages] or " +
                        "[struts.convention.package.locators]");
            }

            if (LOG.isTraceEnabled()) {
                LOG.trace("Loading action configurations");
                if (actionPackages != null) {
                    LOG.trace("Actions being loaded from action packages: {}", actionPackages);
                }
                if (packageLocators != null) {
                    LOG.trace("Actions being loaded using package locator's: {}", packageLocators);
                }
                if (excludePackages != null) {
                    LOG.trace("Excluding actions from packages: {}", excludePackages);
                }
            }

            Set<Class> classes = findActions();
            buildConfiguration(classes);
        }
    }
