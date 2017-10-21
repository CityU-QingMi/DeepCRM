    protected AnnotatedServerEndpointMetadata(WebSocketContainerScope containerScope, Class<?> websocket, ServerEndpointConfig baseConfig) throws DeploymentException
    {
        super(websocket);

        ServerEndpoint anno = websocket.getAnnotation(ServerEndpoint.class);
        if (anno == null)
        {
            throw new InvalidWebSocketException("Unsupported WebSocket object, missing @" + ServerEndpoint.class + " annotation");
        }

        this.endpoint = anno;
        this.config = new AnnotatedServerEndpointConfig(containerScope,websocket,anno,baseConfig);
        
        getDecoders().addAll(anno.decoders());
        getEncoders().addAll(anno.encoders());
    }
