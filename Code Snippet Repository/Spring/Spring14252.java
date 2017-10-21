	@Test
	public void handleRequestXhrStreaming() throws Exception {
		XhrStreamingTransportHandler transportHandler = new XhrStreamingTransportHandler();
		transportHandler.initialize(this.sockJsConfig);
		AbstractSockJsSession session = transportHandler.createSession("1", this.webSocketHandler, null);

		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertEquals("application/javascript;charset=UTF-8", this.response.getHeaders().getContentType().toString());
		assertTrue("Streaming request not started", this.servletRequest.isAsyncStarted());
		verify(this.webSocketHandler).afterConnectionEstablished(session);
	}
