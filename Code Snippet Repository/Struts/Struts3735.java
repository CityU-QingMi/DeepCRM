    @Override
    protected ApplicationContext createTilesApplicationContext(ApplicationContext preliminaryContext) {
        ServletContext servletContext = (ServletContext) preliminaryContext.getContext();

        if (servletContext.getInitParameter(DefinitionsFactory.DEFINITIONS_CONFIG) != null) {
            LOG.trace("Found definitions config in web.xml, using standard Servlet support ....");
            return new ServletApplicationContext(servletContext);
        } else {
            LOG.trace("Initializing Tiles wildcard support ...");
            return new StrutsWildcardServletApplicationContext(servletContext);
        }
    }
