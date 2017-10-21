    @Before
    public void init() throws Exception
    {
        server = new Server();
        connector = new LocalConnector(server);
        connector.setIdleTimeout(10000);
        server.addConnector(connector);

        ContextHandler vcontext=new ContextHandler();
        vcontext.setContextPath("/");
        vcontext.setVirtualHosts(new String[]
        { "VirtualHost" });
        vcontext.setHandler(new DumpHandler("Virtual Dump"));

        ContextHandler context=new ContextHandler();
        context.setContextPath("/");
        context.setHandler(new DumpHandler());

        HandlerCollection collection=new HandlerCollection();
        collection.setHandlers(new Handler[]
        { vcontext, context });

        server.setHandler(collection);

        server.start();
    }
