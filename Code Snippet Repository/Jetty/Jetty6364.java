    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        // Build up a configuration with a specific path
        // Intentionally using alternate path in config (which differs from @ServerEndpoint declaration)
        String path = "/echo-alt";
        ServerEndpointConfig.Builder builder = ServerEndpointConfig.Builder.create(BasicEchoSocket.class,path);
        try
        {
            container.addEndpoint(builder.build());
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint via config file",e);
        }
    }
