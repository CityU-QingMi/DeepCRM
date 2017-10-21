	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.webSocketHandler = new SubProtocolWebSocketHandler(this.inClientChannel, this.outClientChannel);
		given(stompHandler.getSupportedProtocols()).willReturn(Arrays.asList("v10.stomp", "v11.stomp", "v12.stomp"));
		given(mqttHandler.getSupportedProtocols()).willReturn(Arrays.asList("MQTT"));
		this.session = new TestWebSocketSession();
		this.session.setId("1");
		this.session.setOpen(true);
	}
