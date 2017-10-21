	private void handleRequestAndExpectFailure() throws Exception {
		resetResponse();

		WebSocketHandler wsHandler = mock(WebSocketHandler.class);
		AbstractSockJsSession session = new TestHttpSockJsSession("1", new StubSockJsServiceConfig(), wsHandler, null);

		new XhrReceivingTransportHandler().handleRequest(this.request, this.response, wsHandler, session);

		assertEquals(500, this.servletResponse.getStatus());
		verifyNoMoreInteractions(wsHandler);
	}
