    public JsrEventDriverFactory(WebSocketContainerScope containerScope)
    {
        super(containerScope);

        clearImplementations();
        // Classes that extend javax.websocket.Endpoint
        addImplementation(new JsrEndpointImpl());
        // Classes annotated with @javax.websocket.ClientEndpoint
        addImplementation(new JsrClientEndpointImpl());
    }
