    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy)
    {
        if (!(websocket instanceof EndpointInstance))
        {
            throw new IllegalStateException(String.format("Websocket %s must be an %s",websocket.getClass().getName(),EndpointInstance.class.getName()));
        }
        
        EndpointInstance ei = (EndpointInstance)websocket;
        JsrEndpointEventDriver driver = new JsrEndpointEventDriver(policy, ei);
        
        ServerEndpointConfig config = (ServerEndpointConfig)ei.getConfig();
        if (config instanceof PathParamServerEndpointConfig)
        {
            PathParamServerEndpointConfig ppconfig = (PathParamServerEndpointConfig)config;
            driver.setPathParameters(ppconfig.getPathParamMap());
        }

        return driver;
    }
