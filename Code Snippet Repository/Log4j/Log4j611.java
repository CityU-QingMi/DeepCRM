    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        LOGGER.debug("Stopping LoggerContext[name={}, {}]...", getName(), this);
        configLock.lock();
        try {
            if (this.isStopped()) {
                return true;
            }

            this.setStopping();
            try {
                Server.unregisterLoggerContext(getName()); // LOG4J2-406, LOG4J2-500
            } catch (final LinkageError | Exception e) {
                // LOG4J2-1506 Hello Android, GAE
                LOGGER.error("Unable to unregister MBeans", e);
            }
            if (shutdownCallback != null) {
                shutdownCallback.cancel();
                shutdownCallback = null;
            }
            final Configuration prev = configuration;
            configuration = NULL_CONFIGURATION;
            updateLoggers();
            if (prev instanceof LifeCycle2) {
                ((LifeCycle2) prev).stop(timeout, timeUnit);
            } else {
                prev.stop();
            }
            externalContext = null;
            LogManager.getFactory().removeContext(this);
        } finally {
            configLock.unlock();
            this.setStopped();
        }
        LOGGER.debug("Stopped LoggerContext[name={}, {}] with status {}", getName(), this, true);
        return true;
    }
