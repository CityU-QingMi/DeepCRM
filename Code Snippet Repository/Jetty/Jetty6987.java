    @BeforeClass
    public static void startServer() throws Exception
    {
        decoratorsCreator = new DecoratorsCreator();
        server = new SimpleServletServer(new DecoratorsRequestServlet(decoratorsCreator))
        {
            @Override
            protected void configureServletContextHandler(ServletContextHandler context)
            {
                // Add decorator in the new util way
                context.getObjectFactory().clear();
                context.getObjectFactory().addDecorator(new DummyUtilDecorator());
            }
        };
        server.start();
    }
