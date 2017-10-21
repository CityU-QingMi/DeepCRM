	@Before
	public void setup() throws Exception {

		this.server = new TestJettyWebSocketServer(new TextWebSocketHandler());
		this.server.start();

		this.client = new JettyWebSocketClient();
		this.client.start();

		this.wsUrl = "ws://localhost:" + this.server.getPort() + "/test";
	}
