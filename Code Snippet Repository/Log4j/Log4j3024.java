    private void initializeNonJndi(final String location) {
        if (this.name == null) {
            this.name = this.servletContext.getServletContextName();
            LOGGER.debug("Using the servlet context name \"{}\".", this.name);
        }
        if (this.name == null) {
            this.name = this.servletContext.getContextPath();
            LOGGER.debug("Using the servlet context context-path \"{}\".", this.name);
        }

        if (this.name == null && location == null) {
            LOGGER.error("No Log4j context configuration provided. This is very unusual.");
            this.name = new SimpleDateFormat("yyyyMMdd_HHmmss.SSS").format(new Date());
        }

        final URI uri = getConfigURI(location);
        this.loggerContext = Configurator.initialize(this.name, this.getClassLoader(), uri, this.servletContext);
    }
