	@Test
	public void handleRequestXhr() throws Exception {
		XhrPollingTransportHandler transportHandler = new XhrPollingTransportHandler();
		transportHandler.initialize(this.sockJsConfig);

		AbstractSockJsSession session = transportHandler.createSession("1", this.webSocketHandler, null);
		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertEquals("application/javascript;charset=UTF-8", this.response.getHeaders().getContentType().toString());
		assertEquals("o\n", this.servletResponse.getContentAsString());
		assertFalse("Polling request should complete after open frame", this.servletRequest.isAsyncStarted());
		verify(this.webSocketHandler).afterConnectionEstablished(session);

		resetRequestAndResponse();
		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertTrue("Polling request should remain open", this.servletRequest.isAsyncStarted());
		verify(this.taskScheduler).schedule(any(Runnable.class), any(Date.class));

		resetRequestAndResponse();
		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertFalse("Request should have been rejected", this.servletRequest.isAsyncStarted());
		assertEquals("c[2010,\"Another connection still open\"]\n", this.servletResponse.getContentAsString());
	}
