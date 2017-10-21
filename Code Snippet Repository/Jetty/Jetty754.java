    @Override
    public void processBinding(Node node, App app) throws Exception
    {
        ContextHandler handler = app.getContextHandler();
        
        // start the handler
        handler.start();
        
        // After starting let the context manage state
        app.getDeploymentManager().getContexts().manage(handler);
    }
