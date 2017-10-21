    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServerContainer container = (ServerContainer)sce.getServletContext().getAttribute(ServerContainer.class.getName());
        try
        {
            container.addEndpoint(BasicEchoSocket.class);
        }
        catch (DeploymentException e)
        {
            throw new RuntimeException("Unable to add endpoint directly",e);
        }
    }
