	@Before
	public void setUp() throws Exception {

		logger.debug("Setting up before '" + this.testName.getMethodName() + "'");

		this.wac = new AnnotationConfigWebApplicationContext();
		this.wac.register(TestConfig.class);
		this.wac.refresh();

		this.server = new TomcatWebSocketTestServer();
		this.server.setup();
		this.server.deployConfig(this.wac);
		this.server.start();

		WebSocketClient webSocketClient = new StandardWebSocketClient();
		this.stompClient = new WebSocketStompClient(webSocketClient);
		this.stompClient.setMessageConverter(new StringMessageConverter());
	}
