    @Override
    public void setConfigText(final String configText, final String charsetName) {
        LOGGER.debug("---------");
        LOGGER.debug("Remote request to reconfigure from config text.");

        try {
            final InputStream in = new ByteArrayInputStream(configText.getBytes(charsetName));
            final ConfigurationSource source = new ConfigurationSource(in);
            final Configuration updated = ConfigurationFactory.getInstance().getConfiguration(loggerContext, source);
            loggerContext.start(updated);
            LOGGER.debug("Completed remote request to reconfigure from config text.");
        } catch (final Exception ex) {
            final String msg = "Could not reconfigure from config text";
            LOGGER.error(msg, ex);
            throw new IllegalArgumentException(msg, ex);
        }
    }
