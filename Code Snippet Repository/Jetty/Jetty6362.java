    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        if (container==null)
            throw new IllegalStateException("No Websocket ServerContainer in "+sce.getServletContext());
        
        // Build up a configuration with a specific path
        String path = "/echo";
        ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.create(BasicEchoEndpoint.class,path);
        try
        {
            container.addEndpoint(builder.build());
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint via config file",e);
        }
    }
