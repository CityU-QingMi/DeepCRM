    public void contextInitialized(ServletContextEvent servletContextEvent) {
        loaded = true;

        try {
            PlexusContainer pc = new DefaultPlexusContainer();
            PlexusUtils.configure(pc, "plexus-application.xml");
            ServletContext ctx = servletContextEvent.getServletContext();
            ctx.setAttribute(KEY, pc);

            pc.initialize();
            pc.start();
        } catch (Exception e) {
            LOG.error("Error initializing plexus container (scope: application)", e);
        }
    }
