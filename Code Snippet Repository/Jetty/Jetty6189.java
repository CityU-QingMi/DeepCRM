    @Before
    public void initSession()
    {
        container = new ClientContainer();
        String id = JsrSessionTest.class.getSimpleName();
        URI requestURI = URI.create("ws://localhost/" + id);
        WebSocketPolicy policy = WebSocketPolicy.newClientPolicy();
        ClientEndpointConfig config = new EmptyClientEndpointConfig();
        DummyEndpoint websocket = new DummyEndpoint();
        SimpleEndpointMetadata metadata = new SimpleEndpointMetadata(websocket.getClass());
        // Executor executor = null;

        EndpointInstance ei = new EndpointInstance(websocket,config,metadata);
        
        EventDriver driver = new JsrEndpointEventDriver(policy,ei);
        DummyConnection connection = new DummyConnection();
        session = new JsrSession(container,id,requestURI,driver,connection);
    }
