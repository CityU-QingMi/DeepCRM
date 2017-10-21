    private void init_TraditionalXmlConfigurations() {
        String configPaths = initParams.get("config");
        if (configPaths == null) {
            configPaths = DEFAULT_CONFIGURATION_PATHS;
        }
        String[] files = configPaths.split("\\s*[,]\\s*");
        for (String file : files) {
            if (file.endsWith(".xml")) {
                configurationManager.addContainerProvider(createStrutsXmlConfigurationProvider(file, false, servletContext));
            } else {
                throw new IllegalArgumentException("Invalid configuration file name");
            }
        }
    }
