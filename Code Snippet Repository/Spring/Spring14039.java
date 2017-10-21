	@Test
	public void openConnection() throws Exception {
		List<String> subprotocols = Arrays.asList("abc");

		TestLifecycleWebSocketClient client = new TestLifecycleWebSocketClient(false);
		WebSocketHandler handler = new TextWebSocketHandler();

		WebSocketConnectionManager manager = new WebSocketConnectionManager(client, handler , "/path/{id}", "123");
		manager.setSubProtocols(subprotocols);
		manager.openConnection();

		WebSocketHttpHeaders expectedHeaders = new WebSocketHttpHeaders();
		expectedHeaders.setSecWebSocketProtocol(subprotocols);

		assertEquals(expectedHeaders, client.headers);
		assertEquals(new URI("/path/123"), client.uri);

		WebSocketHandlerDecorator loggingHandler = (WebSocketHandlerDecorator) client.webSocketHandler;
		assertEquals(LoggingWebSocketHandlerDecorator.class, loggingHandler.getClass());

		assertSame(handler, loggingHandler.getDelegate());
	}
