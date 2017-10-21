    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        // Build up a configuration with a specific path
        String path = "/idle-onopen-endpoint";
        ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.create(OnOpenIdleTimeoutEndpoint.class,path);
        try
        {
            container.addEndpoint(builder.build());
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint via config file",e);
        }
    }
