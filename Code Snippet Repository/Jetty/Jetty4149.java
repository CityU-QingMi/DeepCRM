    @Before
    public void startServer() throws Exception
    {
        complete=new CountDownLatch(1);
        ex0.set(null);
        ex1.set(null);
        posted.set(false);
        server = new Server();
        connector = new LocalConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(BasicReadPostServlet.class, "/post");

        server.setHandler(context);

        server.start();
    }
