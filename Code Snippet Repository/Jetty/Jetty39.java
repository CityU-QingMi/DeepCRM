    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);

        // Create a ServletContext, with a session handler enabled.
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        server.setHandler(context);

        // Access the SessionHandler from the context.
        SessionHandler sessions = context.getSessionHandler();
        
        // Explicitly set Session Cache and null Datastore.
        // This is normally done by default,
        // but is done explicitly here for demonstration.
        // If more than one context is to be deployed, it is
        // simpler to use SessionCacheFactory and/or
        // SessionDataStoreFactory instances set as beans on 
        // the server.
        SessionCache cache = new DefaultSessionCache(sessions);
        cache.setSessionDataStore(new NullSessionDataStore());
        sessions.setSessionCache(cache);

        // Servlet to read/set the greeting stored in the session.
        // Can be accessed using http://localhost:8080/hello
        context.addServlet(HelloSessionServlet.class, "/");

        server.start();
        server.join();
    }
