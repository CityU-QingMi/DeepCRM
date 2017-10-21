    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        try
        {
            Configurator config = new Config();
            
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongMessageEndpoint.class,"/ping").configurator(config).build());
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongMessageEndpoint.class,"/pong").configurator(config).build());
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongSocket.class,"/ping-socket").build());
            container.addEndpoint(ServerEndpointConfig.Builder.create(PongSocket.class,"/pong-socket").build());
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint directly",e);
        }
    }
