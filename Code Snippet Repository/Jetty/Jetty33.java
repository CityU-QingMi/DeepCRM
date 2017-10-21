    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        // Setup JMX
        MBeanContainer mbContainer = new MBeanContainer(
                ManagementFactory.getPlatformMBeanServer());
        server.addBean(mbContainer, true);

        // Declare server handler collection
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        server.setHandler(contexts);

        // Configure context "/" (root) for servlets
        ServletContextHandler root = new ServletContextHandler(contexts, "/",
                ServletContextHandler.SESSIONS);
        // Add servlets to root context
        root.addServlet(new ServletHolder(new HelloServlet("Hello")), "/");
        root.addServlet(new ServletHolder(new HelloServlet("Ciao")), "/it/*");
        root.addServlet(new ServletHolder(new HelloServlet("Bonjoir")), "/fr/*");

        // Configure context "/other" for servlets
        ServletContextHandler other = new ServletContextHandler(contexts,
                "/other", ServletContextHandler.SESSIONS);
        // Add servlets to /other context
        other.addServlet(DefaultServlet.class.getCanonicalName(), "/");
        other.addServlet(new ServletHolder(new HelloServlet("YO!")), "*.yo");

        server.start();
        server.dumpStdErr();
        server.join();
    }
