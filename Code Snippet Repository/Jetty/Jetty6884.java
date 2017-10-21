    @Before
    public void setupSession() throws Exception
    {
        policy = WebSocketPolicy.newServerPolicy();
        policy.setInputBufferSize(1024);
        policy.setMaxTextMessageBufferSize(1024);

        // Container
        WebSocketContainerScope containerScope = new SimpleContainerScope(policy,bufferPool);
    
        // Event Driver factory
        EventDriverFactory factory = new EventDriverFactory(containerScope);
    
        // local socket
        EventDriver driver = factory.wrap(new TrackingSocket("local"));

        // remote socket
        socket = new TrackingSocket("remote");
        OutgoingFrames socketPipe = FramePipes.to(factory.wrap(socket));

        session = new LocalWebSocketSession(containerScope,testname,driver);

        session.setPolicy(policy);
        // talk to our remote socket
        session.setOutgoingHandler(socketPipe);
        // start session
        session.start();
        // open connection
        session.open();
    }
