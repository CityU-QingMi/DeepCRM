    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);
        // Add JMX tracking to Server
        server.addBean(new MBeanContainer(ManagementFactory
                .getPlatformMBeanServer()));

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(DumpServlet.class, "/dump/*");
        context.addServlet(DefaultServlet.class, "/");

        // Add Connector Statistics tracking to all connectors
        ServerConnectionStatistics.addToAllConnectors(server);

        server.start();
        server.join();
    }
