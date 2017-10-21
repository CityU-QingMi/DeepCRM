	@Test
	public void webSocketHandlerDecorator() throws Exception {
		ApplicationContext config = createConfig(WebSocketHandlerDecoratorConfig.class);
		WebSocketHandler handler = config.getBean(SubProtocolWebSocketHandler.class);
		assertNotNull(handler);

		SimpleUrlHandlerMapping mapping = (SimpleUrlHandlerMapping) config.getBean("stompWebSocketHandlerMapping");
		WebSocketHttpRequestHandler httpHandler = (WebSocketHttpRequestHandler) mapping.getHandlerMap().get("/test");
		handler = httpHandler.getWebSocketHandler();

		WebSocketSession session = new TestWebSocketSession("id");
		handler.afterConnectionEstablished(session);
		assertEquals(true, session.getAttributes().get("decorated"));
	}
