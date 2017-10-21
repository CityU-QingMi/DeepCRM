    public static void main(String[] args) throws Exception
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8888);
        server.addConnector(connector);

        // Setup proxy handler to handle CONNECT methods
        ConnectHandler proxy = new ConnectHandler();
//        proxy.setWhite(new String[]{"mail.google.com"});
//        proxy.addWhitelistHost("www.google.com");
        server.setHandler(proxy);

        // Setup proxy servlet
        ServletContextHandler context = new ServletContextHandler(proxy, "/", ServletContextHandler.SESSIONS);
        ServletHolder proxyServlet = new ServletHolder(ProxyServlet.class);
//        proxyServlet.setInitParameter("whiteList", "google.com, www.eclipse.org, localhost");
//        proxyServlet.setInitParameter("blackList", "google.com/calendar/*, www.eclipse.org/committers/");
        context.addServlet(proxyServlet, "/*");

        server.start();
    }
