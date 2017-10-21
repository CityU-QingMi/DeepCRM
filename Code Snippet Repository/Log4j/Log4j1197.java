    @Override
    public void setConfigLocationUri(final String configLocation) throws URISyntaxException, IOException {
        if (configLocation == null || configLocation.isEmpty()) {
            throw new IllegalArgumentException("Missing configuration location");
        }
        LOGGER.debug("---------");
        LOGGER.debug("Remote request to reconfigure using location " + configLocation);
        final File configFile = new File(configLocation);
        ConfigurationSource configSource = null;
        if (configFile.exists()) {
            LOGGER.debug("Opening config file {}", configFile.getAbsolutePath());
            configSource = new ConfigurationSource(new FileInputStream(configFile), configFile);
        } else {
            final URL configURL = new URL(configLocation);
            LOGGER.debug("Opening config URL {}", configURL);
            configSource = new ConfigurationSource(configURL.openStream(), configURL);
        }
        final Configuration config = ConfigurationFactory.getInstance().getConfiguration(loggerContext, configSource);
        loggerContext.start(config);
        LOGGER.debug("Completed remote request to reconfigure.");
    }
