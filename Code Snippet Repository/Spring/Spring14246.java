	@Test
	public void delegateMessageException() throws Exception {
		StubSockJsServiceConfig sockJsConfig = new StubSockJsServiceConfig();
		this.servletRequest.setContent("[\"x\"]".getBytes("UTF-8"));

		WebSocketHandler wsHandler = mock(WebSocketHandler.class);
		TestHttpSockJsSession session = new TestHttpSockJsSession("1", sockJsConfig, wsHandler, null);
		session.delegateConnectionEstablished();

		willThrow(new Exception()).given(wsHandler).handleMessage(session, new TextMessage("x"));

		try {
			XhrReceivingTransportHandler transportHandler = new XhrReceivingTransportHandler();
			transportHandler.initialize(sockJsConfig);
			transportHandler.handleRequest(this.request, this.response, wsHandler, session);
			fail("Expected exception");
		}
		catch (SockJsMessageDeliveryException ex) {
			assertNull(session.getCloseStatus());
		}
	}
