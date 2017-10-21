    public JsrSession(ClientContainer container, String id, URI requestURI, EventDriver websocket, LogicalConnection connection)
    {
        super(container, requestURI, websocket, connection);
        if (!(websocket instanceof AbstractJsrEventDriver))
        {
            throw new IllegalArgumentException("Cannot use, not a JSR WebSocket: " + websocket);
        }
        AbstractJsrEventDriver jsr = (AbstractJsrEventDriver)websocket;
        this.config = jsr.getConfig();
        this.metadata = jsr.getMetadata();
        this.container = container;
        this.id = id;
        this.decoderFactory = new DecoderFactory(this,metadata.getDecoders(),container.getDecoderFactory());
        this.encoderFactory = new EncoderFactory(this,metadata.getEncoders(),container.getEncoderFactory());
        this.messageHandlerFactory = new MessageHandlerFactory();
        this.wrappers = new MessageHandlerWrapper[MessageType.values().length];
        this.messageHandlerSet = new HashSet<>();
    }
