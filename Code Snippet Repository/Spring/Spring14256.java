	@Test
	public void getSubProtocols() throws Exception {
		SubscribableChannel channel = mock(SubscribableChannel.class);
		SubProtocolWebSocketHandler handler = new SubProtocolWebSocketHandler(channel, channel);
		StompSubProtocolHandler stompHandler = new StompSubProtocolHandler();
		handler.addProtocolHandler(stompHandler);

		TaskScheduler scheduler = mock(TaskScheduler.class);
		DefaultSockJsService service = new DefaultSockJsService(scheduler);
		WebSocketServerSockJsSession session = new WebSocketServerSockJsSession("1", service, handler, null);
		SockJsWebSocketHandler sockJsHandler = new SockJsWebSocketHandler(service, handler, session);

		assertEquals(stompHandler.getSupportedProtocols(), sockJsHandler.getSubProtocols());
	}
