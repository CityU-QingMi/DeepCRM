	@Before
	public void setup() {
		this.session = mock(Session.class);
		given(this.session.getUpgradeRequest()).willReturn(Mockito.mock(UpgradeRequest.class));
		given(this.session.getUpgradeResponse()).willReturn(Mockito.mock(UpgradeResponse.class));

		this.webSocketHandler = mock(WebSocketHandler.class);
		this.webSocketSession = new JettyWebSocketSession(null, null);
		this.adapter = new JettyWebSocketHandlerAdapter(this.webSocketHandler, this.webSocketSession);
	}
