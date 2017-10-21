    public ServerEndpointMetadata getServerEndpointMetadata(final Class<?> endpoint, final ServerEndpointConfig config) throws DeploymentException
    {
        ServerEndpointMetadata metadata = null;

        ServerEndpoint anno = endpoint.getAnnotation(ServerEndpoint.class);
        if (anno != null)
        {
            // Annotated takes precedence here
            AnnotatedServerEndpointMetadata ametadata = new AnnotatedServerEndpointMetadata(this,endpoint,config);
            AnnotatedEndpointScanner<ServerEndpoint, ServerEndpointConfig> scanner = new AnnotatedEndpointScanner<>(ametadata);
            metadata = ametadata;
            scanner.scan();
        }
        else if (Endpoint.class.isAssignableFrom(endpoint))
        {
            // extends Endpoint
            @SuppressWarnings("unchecked")
            Class<? extends Endpoint> eendpoint = (Class<? extends Endpoint>)endpoint;
            metadata = new SimpleServerEndpointMetadata(eendpoint,config);
        }
        else
        {
            StringBuilder err = new StringBuilder();
            err.append("Not a recognized websocket [");
            err.append(endpoint.getName());
            err.append("] does not extend @").append(ServerEndpoint.class.getName());
            err.append(" or extend from ").append(Endpoint.class.getName());
            throw new DeploymentException("Unable to identify as valid Endpoint: " + endpoint);
        }

        return metadata;
    }
