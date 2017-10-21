    public ClientContainer(WebSocketClient client)
    {
        this.scopeDelegate = client;
        this.client = client;
        this.internalClient = false;
        
        this.endpointClientMetadataCache = new ConcurrentHashMap<>();
        this.decoderFactory = new DecoderFactory(this,PrimitiveDecoderMetadataSet.INSTANCE);
        this.encoderFactory = new EncoderFactory(this,PrimitiveEncoderMetadataSet.INSTANCE);
    }
