    @Override
    public synchronized void start() {
        if (this.isStopped() || this.isStopping()) {
            throw new IllegalStateException("Cannot start this Log4jWebInitializerImpl after it was stopped.");
        }

        // only do this once
        if (this.isInitialized()) {
            super.setStarting();

            this.name = this.substitutor.replace(this.servletContext.getInitParameter(LOG4J_CONTEXT_NAME));
            final String location = this.substitutor.replace(this.servletContext
                    .getInitParameter(LOG4J_CONFIG_LOCATION));
            final boolean isJndi = "true".equalsIgnoreCase(this.servletContext
                    .getInitParameter(IS_LOG4J_CONTEXT_SELECTOR_NAMED));

            if (isJndi) {
                this.initializeJndi(location);
            } else {
                this.initializeNonJndi(location);
            }
            if (this.loggerContext instanceof AsyncLoggerContext) {
                ((AsyncLoggerContext) this.loggerContext).setUseThreadLocals(false);
            }

            this.servletContext.setAttribute(CONTEXT_ATTRIBUTE, this.loggerContext);
            super.setStarted();
        }
    }
