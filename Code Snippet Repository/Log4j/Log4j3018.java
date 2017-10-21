    @Override
    public void onStartup(final Set<Class<?>> classes, final ServletContext servletContext) throws ServletException {
        if (servletContext.getMajorVersion() > 2 && servletContext.getEffectiveMajorVersion() > 2 &&
                !"true".equalsIgnoreCase(servletContext.getInitParameter(
                        Log4jWebSupport.IS_LOG4J_AUTO_INITIALIZATION_DISABLED
                ))) {
            LOGGER.debug("Log4jServletContainerInitializer starting up Log4j in Servlet 3.0+ environment.");

            final FilterRegistration.Dynamic filter =
                    servletContext.addFilter("log4jServletFilter", Log4jServletFilter.class);
            if (filter == null) {
                LOGGER.warn("WARNING: In a Servlet 3.0+ application, you should not define a " +
                    "log4jServletFilter in web.xml. Log4j 2 normally does this for you automatically. Log4j 2 " +
                    "web auto-initialization has been canceled.");
                return;
            }

            final Log4jWebLifeCycle initializer = WebLoggerContextUtils.getWebLifeCycle(servletContext);
            initializer.start();
            initializer.setLoggerContext(); // the application is just now starting to start up

            servletContext.addListener(new Log4jServletContextListener());

            filter.setAsyncSupported(true); // supporting async when the user isn't using async has no downsides
            filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        }
    }
