    @Override
    public void configure(WebSocketServletFactory factory)
    {
        // Setup some extensions we want to test against
        factory.getExtensionFactory().register("permessage-compress",PerMessageDeflateExtension.class);

        // Setup the desired Socket to use for all incoming upgrade requests
        factory.register(EchoSocket.class);
        
        // Some alternate sizes
        factory.getPolicy().setMaxBinaryMessageSize(2222);
        factory.getPolicy().setMaxTextMessageSize(4444);
    }
