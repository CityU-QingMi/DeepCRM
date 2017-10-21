    @Override
    public void addEndpoint(Class<?> endpointClass) throws DeploymentException
    {
        if (isStarted() || isStarting())
        {
            ServerEndpointMetadata metadata = getServerEndpointMetadata(endpointClass,null);
            addEndpoint(metadata);
        }
        else
        {
            if (deferredEndpointClasses == null)
            {
                deferredEndpointClasses = new ArrayList<Class<?>>();
            }
            deferredEndpointClasses.add(endpointClass);
        }
    }
