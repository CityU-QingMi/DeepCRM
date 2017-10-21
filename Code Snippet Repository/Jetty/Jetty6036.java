    @Override
    protected void doStart() throws Exception
    {
        super.doStart();
        
        // Initialize the default decoder / encoder factories
        EmptyClientEndpointConfig empty = new EmptyClientEndpointConfig();
        this.decoderFactory.init(empty);
        this.encoderFactory.init(empty);
    }
