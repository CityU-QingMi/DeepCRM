	@Test
	public void afterConnectionEstablished() throws Exception {

		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.refresh();

		EchoHandler.reset();
		PerConnectionWebSocketHandler handler = new PerConnectionWebSocketHandler(EchoHandler.class);
		handler.setBeanFactory(context.getBeanFactory());

		WebSocketSession session = new TestWebSocketSession();
		handler.afterConnectionEstablished(session);

		assertEquals(1, EchoHandler.initCount);
		assertEquals(0, EchoHandler.destroyCount);

		handler.afterConnectionClosed(session, CloseStatus.NORMAL);

		assertEquals(1, EchoHandler.initCount);
		assertEquals(1, EchoHandler.destroyCount);
	}
