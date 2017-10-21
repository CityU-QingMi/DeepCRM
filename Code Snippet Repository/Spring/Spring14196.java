	@Test
	public void fallbackAfterTransportFailure() throws Exception {
		this.testFilter.sendErrorMap.put("/websocket", 200);
		this.testFilter.sendErrorMap.put("/xhr_streaming", 500);
		TestClientHandler handler = new TestClientHandler();
		initSockJsClient(createWebSocketTransport(), createXhrTransport());
		WebSocketSession session = this.sockJsClient.doHandshake(handler, this.baseUrl + "/echo").get();
		assertEquals("Fallback didn't occur", XhrClientSockJsSession.class, session.getClass());
		TextMessage message = new TextMessage("message1");
		session.sendMessage(message);
		handler.awaitMessage(message, 5000);
	}
