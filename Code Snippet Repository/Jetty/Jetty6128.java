    public JsrAnnotatedEventDriver(WebSocketPolicy policy, EndpointInstance endpointInstance, JsrEvents<?, ?> events)
    {
        super(policy,endpointInstance);
        this.events = events;
    
        EndpointMetadata metadata = endpointInstance.getMetadata();
    
        if (metadata.maxTextMessageSize() >= 1)
            policy.setMaxTextMessageSize((int) metadata.maxTextMessageSize());
        if (metadata.maxBinaryMessageSize() >= 1)
            policy.setMaxBinaryMessageSize((int) metadata.maxBinaryMessageSize());
    }
