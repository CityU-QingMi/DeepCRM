    public EventDriver toEventDriver(Object websocket) throws Throwable
    {
        WebSocketPolicy policy = WebSocketPolicy.newServerPolicy();
        policy.setInputBufferSize(1024);
        policy.setMaxBinaryMessageBufferSize(1024);
        policy.setMaxTextMessageBufferSize(1024);


        // Create EventDriver
        EventDriverImpl driverImpl = new JsrServerEndpointImpl();
        Class<?> endpoint = websocket.getClass();
        ServerEndpoint anno = endpoint.getAnnotation(ServerEndpoint.class);
        Assert.assertThat("Endpoint: " + endpoint + " should be annotated with @ServerEndpoint",anno,notNullValue());
        
        WebSocketContainerScope containerScope = new SimpleContainerScope(policy);
        // Event Driver Factory
        EventDriverFactory factory = new EventDriverFactory(containerScope);
        factory.addImplementation(new JsrServerEndpointImpl());
        
        ServerEndpointConfig config = new BasicServerEndpointConfig(containerScope,endpoint,"/");
        AnnotatedServerEndpointMetadata metadata = new AnnotatedServerEndpointMetadata(containerScope,endpoint,config);
        AnnotatedEndpointScanner<ServerEndpoint, ServerEndpointConfig> scanner = new AnnotatedEndpointScanner<>(metadata);
        scanner.scan();
        EndpointInstance ei = new EndpointInstance(websocket,config,metadata);
        EventDriver driver = driverImpl.create(ei,policy);
        Assert.assertThat("EventDriver",driver,notNullValue());

        // Create Local JsrSession
        String id = testname.getMethodName();
        URI requestURI = URI.create("ws://localhost/" + id);
        DummyConnection connection = new DummyConnection();
        ClientContainer container = new ClientContainer();
        container.start();
        
        @SuppressWarnings("resource")
        JsrSession session = new JsrSession(container,id,requestURI,driver,connection);
        session.start();
        session.open();
        return driver;
    }
