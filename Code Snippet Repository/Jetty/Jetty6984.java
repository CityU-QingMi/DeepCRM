    @BeforeClass
    public static void startServer() throws Exception
    {
        decoratorsCreator = new DecoratorsCreator();
        server = new SimpleServletServer(new DecoratorsRequestServlet(decoratorsCreator))
        {
            @SuppressWarnings("deprecation")
            @Override
            protected void configureServletContextHandler(ServletContextHandler context)
            {
                context.getObjectFactory().clear();
                // Add decorator in the legacy way
                context.addDecorator(new DummyLegacyDecorator());
            }
        };
        server.start();
    }
