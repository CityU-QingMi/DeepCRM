    @Before
    public void init() throws DeploymentException
    {
        WebSocketContainerScope containerScope = new SimpleContainerScope(WebSocketPolicy.newClientPolicy());
        
        DecoderFactory primitivesFactory = new DecoderFactory(containerScope,PrimitiveDecoderMetadataSet.INSTANCE);
        metadatas = new DecoderMetadataSet();
        decoders = new DecoderFactory(containerScope,metadatas,primitivesFactory);
        factory = new MessageHandlerFactory();
    }
