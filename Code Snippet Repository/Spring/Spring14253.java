	@Test
	public void htmlFileTransport() throws Exception {
		HtmlFileTransportHandler transportHandler = new HtmlFileTransportHandler();
		transportHandler.initialize(this.sockJsConfig);
		StreamingSockJsSession session = transportHandler.createSession("1", this.webSocketHandler, null);

		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertEquals(500, this.servletResponse.getStatus());
		assertEquals("\"callback\" parameter required", this.servletResponse.getContentAsString());

		resetRequestAndResponse();
		setRequest("POST", "/");
		this.servletRequest.setQueryString("c=callback");
		this.servletRequest.addParameter("c", "callback");
		transportHandler.handleRequest(this.request, this.response, this.webSocketHandler, session);

		assertEquals("text/html;charset=UTF-8", this.response.getHeaders().getContentType().toString());
		assertTrue("Streaming request not started", this.servletRequest.isAsyncStarted());
		verify(this.webSocketHandler).afterConnectionEstablished(session);
	}
