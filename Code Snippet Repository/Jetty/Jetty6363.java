    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        
        try
        {
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongMessageEndpoint.class,"/ping").build());
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongMessageEndpoint.class,"/pong").build());
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint via config file",e);
        }
    }
