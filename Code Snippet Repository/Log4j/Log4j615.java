    @Override
    public synchronized void onChange(final Reconfigurable reconfigurable) {
        LOGGER.debug("Reconfiguration started for context {} ({})", contextName, this);
        final Configuration newConfig = reconfigurable.reconfigure();
        if (newConfig != null) {
            setConfiguration(newConfig);
            LOGGER.debug("Reconfiguration completed for {} ({})", contextName, this);
        } else {
            LOGGER.debug("Reconfiguration failed for {} ({})", contextName, this);
        }
    }
