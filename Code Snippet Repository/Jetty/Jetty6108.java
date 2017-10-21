    @Override
    public EventDriver create(Object websocket, WebSocketPolicy policy) throws DeploymentException
    {
        if (!(websocket instanceof EndpointInstance))
        {
            throw new IllegalStateException(String.format("Websocket %s must be an %s",websocket.getClass().getName(),EndpointInstance.class.getName()));
        }

        EndpointInstance ei = (EndpointInstance)websocket;
        AnnotatedClientEndpointMetadata metadata = (AnnotatedClientEndpointMetadata)ei.getMetadata();
        JsrEvents<ClientEndpoint, ClientEndpointConfig> events = new JsrEvents<>(metadata);

        // Handle @OnMessage maxMessageSizes
        int maxBinaryMessage = getMaxMessageSize(policy.getMaxBinaryMessageSize(),metadata.maxBinaryMessageSize());
        int maxTextMessage = getMaxMessageSize(policy.getMaxTextMessageSize(),metadata.maxTextMessageSize());

        policy.setMaxBinaryMessageSize(maxBinaryMessage);
        policy.setMaxTextMessageSize(maxTextMessage);

        return new JsrAnnotatedEventDriver(policy,ei,events);
    }
