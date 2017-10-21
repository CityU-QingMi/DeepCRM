	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		WebSocketClient webSocketClient = mock(WebSocketClient.class);
		this.stompClient = new TestWebSocketStompClient(webSocketClient);
		this.stompClient.setTaskScheduler(this.taskScheduler);
		this.stompClient.setStompSession(this.stompSession);

		this.webSocketHandlerCaptor = ArgumentCaptor.forClass(WebSocketHandler.class);
		this.handshakeFuture = new SettableListenableFuture<>();
		when(webSocketClient.doHandshake(this.webSocketHandlerCaptor.capture(), any(), any(URI.class)))
				.thenReturn(this.handshakeFuture);
	}
