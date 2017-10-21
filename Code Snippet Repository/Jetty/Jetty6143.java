    @Override
    protected String getClassName(Object websocket)
    {
        if (websocket instanceof EndpointInstance)
        {
            EndpointInstance ce = (EndpointInstance)websocket;
            return ce.getEndpoint().getClass().getName();
        }

        return websocket.getClass().getName();
    }
