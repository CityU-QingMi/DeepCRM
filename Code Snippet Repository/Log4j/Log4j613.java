    private Configuration setConfiguration(final Configuration config) {
        if (config == null) {
            LOGGER.error("No configuration found for context '{}'.", contextName);
            // No change, return the current configuration.
            return this.configuration;
        }
        configLock.lock();
        try {
            final Configuration prev = this.configuration;
            config.addListener(this);

            final ConcurrentMap<String, String> map = config.getComponent(Configuration.CONTEXT_PROPERTIES);

            try { // LOG4J2-719 network access may throw android.os.NetworkOnMainThreadException
                map.putIfAbsent("hostName", NetUtils.getLocalHostname());
            } catch (final Exception ex) {
                LOGGER.debug("Ignoring {}, setting hostName to 'unknown'", ex.toString());
                map.putIfAbsent("hostName", "unknown");
            }
            map.putIfAbsent("contextName", contextName);
            config.start();
            this.configuration = config;
            updateLoggers();
            if (prev != null) {
                prev.removeListener(this);
                prev.stop();
            }

            firePropertyChangeEvent(new PropertyChangeEvent(this, PROPERTY_CONFIG, prev, config));

            try {
                Server.reregisterMBeansAfterReconfigure();
            } catch (final LinkageError | Exception e) {
                // LOG4J2-716: Android has no java.lang.management
                LOGGER.error("Could not reconfigure JMX", e);
            }
            // AsyncLoggers update their nanoClock when the configuration changes
            Log4jLogEvent.setNanoClock(configuration.getNanoClock());

            return prev;
        } finally {
            configLock.unlock();
        }
    }
