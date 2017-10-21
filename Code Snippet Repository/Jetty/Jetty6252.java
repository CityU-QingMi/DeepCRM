    @Override
    public void addEndpoint(ServerEndpointConfig config) throws DeploymentException
    {
        if (isStarted() || isStarting())
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug("addEndpoint({}) path={} endpoint={}",config,config.getPath(),config.getEndpointClass());
            }
            ServerEndpointMetadata metadata = getServerEndpointMetadata(config.getEndpointClass(),config);
            addEndpoint(metadata);
        }
        else
        {
            if (deferredEndpointConfigs == null)
            {
                deferredEndpointConfigs = new ArrayList<ServerEndpointConfig>();
            }
            deferredEndpointConfigs.add(config);
        }
    }
