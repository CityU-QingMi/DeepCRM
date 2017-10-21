    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String platform = servletContext.getInitParameter(PLATFORM_KEY);
        LOG.debug("Defined OSGi platform as [{}] via context-param [{}]", platform, PLATFORM_KEY);

        osgiHost = OsgiHostFactory.createOsgiHost(platform);
        servletContext.setAttribute(OSGI_HOST, osgiHost);
        try {
            osgiHost.init(servletContext);
        } catch (Exception e) {
            throw new StrutsException("Cannot init OSGi platform!", e);
        }
    }
