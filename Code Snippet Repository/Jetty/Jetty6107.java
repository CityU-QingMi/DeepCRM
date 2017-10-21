    public AnnotatedClientEndpointMetadata(ClientContainer container, Class<?> websocket)
    {
        super(websocket);

        ClientEndpoint anno = websocket.getAnnotation(ClientEndpoint.class);
        if (anno == null)
        {
            throw new InvalidWebSocketException(String.format("Unsupported WebSocket object [%s], missing @%s annotation",websocket.getName(),
                    ClientEndpoint.class.getName()));
        }

        this.endpoint = anno;
        this.config = new AnnotatedClientEndpointConfig(anno);

        getDecoders().addAll(anno.decoders());
        getEncoders().addAll(anno.encoders());
    }
