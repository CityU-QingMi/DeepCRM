    private void setUpShutdownHook() {
        if (shutdownCallback == null) {
            final LoggerContextFactory factory = LogManager.getFactory();
            if (factory instanceof ShutdownCallbackRegistry) {
                LOGGER.debug(SHUTDOWN_HOOK_MARKER, "Shutdown hook enabled. Registering a new one.");
                try {
                    final long shutdownTimeoutMillis = this.configuration.getShutdownTimeoutMillis();
                    this.shutdownCallback = ((ShutdownCallbackRegistry) factory).addShutdownCallback(new Runnable() {
                        @Override
                        public void run() {
                            @SuppressWarnings("resource")
                            final LoggerContext context = LoggerContext.this;
                            LOGGER.debug(SHUTDOWN_HOOK_MARKER, "Stopping LoggerContext[name={}, {}]",
                                    context.getName(), context);
                            context.stop(shutdownTimeoutMillis, TimeUnit.MILLISECONDS);
                        }

                        @Override
                        public String toString() {
                            return "Shutdown callback for LoggerContext[name=" + LoggerContext.this.getName() + ']';
                        }
                    });
                } catch (final IllegalStateException e) {
                    throw new IllegalStateException(
                            "Unable to register Log4j shutdown hook because JVM is shutting down.", e);
                } catch (final SecurityException e) {
                    LOGGER.error(SHUTDOWN_HOOK_MARKER, "Unable to register shutdown hook due to security restrictions",
                            e);
                }
            }
        }
    }
