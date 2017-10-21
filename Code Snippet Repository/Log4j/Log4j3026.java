    @Override
    public synchronized boolean stop(final long timeout, final TimeUnit timeUnit) {
        if (!this.isStarted() && !this.isStopped()) {
            throw new IllegalStateException("Cannot stop this Log4jWebInitializer because it has not started.");
        }

        // only do this once
        if (this.isStarted()) {
            this.setStopping();
            if (this.loggerContext != null) {
                LOGGER.debug("Removing LoggerContext for [{}].", this.name);
                this.servletContext.removeAttribute(CONTEXT_ATTRIBUTE);
                if (this.namedContextSelector != null) {
                    this.namedContextSelector.removeContext(this.name);
                }
                this.loggerContext.stop(timeout, timeUnit);
                this.loggerContext.setExternalContext(null);
                this.loggerContext = null;
            }
            this.setStopped();
        }
        return super.stop(timeout, timeUnit);
    }
