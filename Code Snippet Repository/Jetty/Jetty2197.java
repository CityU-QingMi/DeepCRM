    public void start(final BundleContext context) throws Exception
    {
        ContextHandler ch = new ContextHandler();
        ch.addEventListener(new ServletContextListener () {

            @Override
            public void contextInitialized(ServletContextEvent sce)
            {
               //System.err.println("Context is initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce)
            {
                //System.err.println("CONTEXT IS DESTROYED!");                
            }
            
        });
        Dictionary props = new Hashtable();
        props.put("contextPath","/acme");
        props.put("Jetty-ContextFilePath", "acme.xml");
        _sr = context.registerService(ContextHandler.class.getName(),ch,props);
    }
