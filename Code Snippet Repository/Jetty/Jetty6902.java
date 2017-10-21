    public BlockheadClient(WebSocketPolicy policy, URI destWebsocketURI) throws URISyntaxException
    {
        Assert.assertThat("Websocket URI scheme",destWebsocketURI.getScheme(),anyOf(is("ws"),is("wss")));
        this.destWebsocketURI = destWebsocketURI;
        if (destWebsocketURI.getScheme().equals("wss"))
        {
            throw new RuntimeException("Sorry, BlockheadClient does not support SSL");
        }
        this.destHttpURI = WSURI.toHttp(destWebsocketURI);

        LOG.debug("WebSocket URI: {}",destWebsocketURI);
        LOG.debug("     HTTP URI: {}",destHttpURI);

        // This is a blockhead client, no point tracking leaks on this object.
        this.bufferPool = new MappedByteBufferPool(8192);
        this.generator = new Generator(policy,bufferPool);
        this.parser = new Parser(policy,bufferPool);

        this.extensionFactory = new WebSocketExtensionFactory(new SimpleContainerScope(policy,bufferPool));
        this.ioState = new IOState();
        this.ioState.addListener(this);
    }
