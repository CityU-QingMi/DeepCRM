    private static void initXWork() {
        String configFilePath = configDir + "/struts.xml";
        File configFile = new File(configFilePath);
        try {
            ConfigurationProvider configProvider = new StrutsXmlConfigurationProvider(configFile.getCanonicalPath(), true, null);
            cm = new ConfigurationManager(Container.DEFAULT_NAME);
            cm.addContainerProvider(new DefaultPropertiesProvider());
            cm.addContainerProvider(new StrutsXmlConfigurationProvider("struts-default.xml", false, null));
            cm.addContainerProvider(configProvider);
            cm.addContainerProvider(new PropertiesConfigurationProvider());
            cm.addContainerProvider(new DefaultBeanSelectionProvider());
            isXWorkStarted = true;
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }
