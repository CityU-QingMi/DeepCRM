	@Test
	public void eventSourceTransport() throws Exception {
		EventSourceTransportHandler transportHandler = new EventSourceTransportHandler();
		transportHandler.initialize(this.sockJsConfig);
		StreamingSockJsSession session = transportHandler.createSession("1", this.webSocketHandler, null);

		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertEquals("text/event-stream;charset=UTF-8", this.response.getHeaders().getContentType().toString());
		assertTrue("Streaming request not started", this.servletRequest.isAsyncStarted());
		verify(this.webSocketHandler).afterConnectionEstablished(session);
	}
