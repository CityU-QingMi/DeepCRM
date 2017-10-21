    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler();
        Resource.setDefaultUseCaches(true);
        Resource base = Resource.newResource("jar:file:src/main/resources/content.jar!/");
        context.setBaseResource(base);
        context.addServlet(new ServletHolder(new DefaultServlet()), "/");
        
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
        server.setHandler(handlers);

        server.start();
        server.join();
    }
