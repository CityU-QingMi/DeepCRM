    @Override
    public void processBinding(Node node, App app) throws Exception
    {
        ContextHandler handler = app.getContextHandler();
        
        // Before stopping, take back management from the context
        app.getDeploymentManager().getContexts().unmanage(handler);
        
        // Stop it
        handler.stop();
    }
