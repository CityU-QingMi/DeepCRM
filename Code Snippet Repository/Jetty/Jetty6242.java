    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy) throws Throwable
    {
        if (!(websocket instanceof EndpointInstance))
        {
            throw new IllegalStateException(String.format("Websocket %s must be an %s",websocket.getClass().getName(),EndpointInstance.class.getName()));
        }

        EndpointInstance ei = (EndpointInstance)websocket;
        AnnotatedServerEndpointMetadata metadata = (AnnotatedServerEndpointMetadata)ei.getMetadata();
        JsrEvents<ServerEndpoint, ServerEndpointConfig> events = new JsrEvents<>(metadata);

        // Handle @OnMessage maxMessageSizes
        int maxBinaryMessage = getMaxMessageSize(policy.getMaxBinaryMessageSize(),metadata.onBinary,metadata.onBinaryStream);
        int maxTextMessage = getMaxMessageSize(policy.getMaxTextMessageSize(),metadata.onText,metadata.onTextStream);

        policy.setMaxBinaryMessageSize(maxBinaryMessage);
        policy.setMaxTextMessageSize(maxTextMessage);

        JsrAnnotatedEventDriver driver = new JsrAnnotatedEventDriver(policy,ei,events);
        // Handle @PathParam values
        ServerEndpointConfig config = (ServerEndpointConfig)ei.getConfig();
        if (config instanceof PathParamServerEndpointConfig)
        {
            PathParamServerEndpointConfig ppconfig = (PathParamServerEndpointConfig)config;
            driver.setPathParameters(ppconfig.getPathParamMap());
        }

        return driver;
    }
