    @Override
    public void contextInitialized(final ServletContextEvent event) {
        this.servletContext = event.getServletContext();
        LOGGER.debug("Log4jServletContextListener ensuring that Log4j starts up properly.");

        this.initializer = WebLoggerContextUtils.getWebLifeCycle(this.servletContext);
        try {
            this.initializer.start();
            this.initializer.setLoggerContext(); // the application is just now starting to start up
        } catch (final IllegalStateException e) {
            throw new IllegalStateException("Failed to initialize Log4j properly.", e);
        }
    }
