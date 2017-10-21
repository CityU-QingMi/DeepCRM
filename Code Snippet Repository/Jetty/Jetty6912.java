    public BlockheadServerConnection(Socket socket)
    {
        this.socket = socket;
        this.incomingFrames = new IncomingFramesCapture();
        this.policy = WebSocketPolicy.newServerPolicy();
        this.policy.setMaxBinaryMessageSize(100000);
        this.policy.setMaxTextMessageSize(100000);
        // This is a blockhead server connection, no point tracking leaks on this object.
        this.bufferPool = new MappedByteBufferPool(BUFFER_SIZE);
        this.parser = new Parser(policy,bufferPool);
        this.parseCount = new AtomicInteger(0);
        this.generator = new Generator(policy,bufferPool,false);
        this.extensionRegistry = new WebSocketExtensionFactory(new SimpleContainerScope(policy,bufferPool));
    }
