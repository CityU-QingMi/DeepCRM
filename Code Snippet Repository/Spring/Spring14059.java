	@Before
	public void setup() {
		SubscribableChannel inChannel = mock(SubscribableChannel.class);
		SubscribableChannel outChannel = mock(SubscribableChannel.class);
		this.webSocketHandler = new SubProtocolWebSocketHandler(inChannel, outChannel);

		WebSocketTransportRegistration transport = new WebSocketTransportRegistration();
		TaskScheduler scheduler = mock(TaskScheduler.class);
		this.endpointRegistry = new WebMvcStompEndpointRegistry(this.webSocketHandler, transport, scheduler);
	}
