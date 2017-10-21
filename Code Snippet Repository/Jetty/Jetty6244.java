    @Override
    public boolean supports(Object websocket)
    {
        if (!(websocket instanceof EndpointInstance))
        {
            return false;
        }

        EndpointInstance ei = (EndpointInstance)websocket;
        Object endpoint = ei.getEndpoint();

        ServerEndpoint anno = endpoint.getClass().getAnnotation(ServerEndpoint.class);
        return (anno != null);
    }
