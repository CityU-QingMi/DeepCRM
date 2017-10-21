    public EndpointInstance newClientEndpointInstance(Object endpoint, ServerEndpointConfig config, String path)
    {
        EndpointMetadata metadata = getClientEndpointMetadata(endpoint.getClass(),config);
        ServerEndpointConfig cec = config;
        if (config == null)
        {
            if (metadata instanceof AnnotatedServerEndpointMetadata)
            {
                cec = ((AnnotatedServerEndpointMetadata)metadata).getConfig();
            }
            else
            {
                cec = new BasicServerEndpointConfig(this,endpoint.getClass(),path);
            }
        }
        return new EndpointInstance(endpoint,cec,metadata);
    }
