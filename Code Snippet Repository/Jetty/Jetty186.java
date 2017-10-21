    public void processBinding(Node node, App app) throws Exception
    {
        ContextHandler handler = app.getContextHandler();
        if (handler == null)
        {
            throw new NullPointerException("No Handler created for App: " + app);
        }

        if (handler instanceof WebAppContext)
        {
            // Do webapp specific init
            WebAppContext webapp = (WebAppContext)handler;
            JettyWeldInitializer.initWebApp(webapp);
        }
        else
        {
            // Do general init
            JettyWeldInitializer.initContext(handler);
        }
    }
