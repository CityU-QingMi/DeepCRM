	private void handleRequest(AbstractHttpReceivingTransportHandler transportHandler) throws Exception {
		WebSocketHandler wsHandler = mock(WebSocketHandler.class);
		AbstractSockJsSession session = new TestHttpSockJsSession("1", new StubSockJsServiceConfig(), wsHandler, null);

		transportHandler.initialize(new StubSockJsServiceConfig());
		transportHandler.handleRequest(this.request, this.response, wsHandler, session);

		assertEquals("text/plain;charset=UTF-8", this.response.getHeaders().getContentType().toString());
		verify(wsHandler).handleMessage(session, new TextMessage("x"));
	}
