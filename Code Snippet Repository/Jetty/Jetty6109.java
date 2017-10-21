    @Override
    public boolean supports(Object websocket)
    {
        if (!(websocket instanceof EndpointInstance))
        {
            return false;
        }

        EndpointInstance ei = (EndpointInstance)websocket;
        Object endpoint = ei.getEndpoint();

        ClientEndpoint anno = endpoint.getClass().getAnnotation(ClientEndpoint.class);
        return (anno != null);
    }
