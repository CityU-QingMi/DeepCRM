	@Test
	public void getSubProtocolsNone() throws Exception {
		WebSocketHandler handler = new TextWebSocketHandler();
		TaskScheduler scheduler = mock(TaskScheduler.class);
		DefaultSockJsService service = new DefaultSockJsService(scheduler);
		WebSocketServerSockJsSession session = new WebSocketServerSockJsSession("1", service, handler, null);
		SockJsWebSocketHandler sockJsHandler = new SockJsWebSocketHandler(service, handler, session);

		assertEquals(Collections.emptyList(), sockJsHandler.getSubProtocols());
	}
