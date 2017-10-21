	@Test
	public void handleTransportRequestXhrSend() throws Exception {
		String sockJsPath = sessionUrlPrefix + "xhr_send";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(404, this.servletResponse.getStatus()); // no session yet

		resetResponse();
		sockJsPath = sessionUrlPrefix + "xhr";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(200, this.servletResponse.getStatus()); // session created
		verify(this.xhrHandler).handleRequest(this.request, this.response, this.wsHandler, this.session);

		resetResponse();
		sockJsPath = sessionUrlPrefix + "xhr_send";
		setRequest("POST", sockJsPrefix + sockJsPath);
		given(this.xhrSendHandler.checkSessionType(this.session)).willReturn(true);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(200, this.servletResponse.getStatus()); // session exists
		verify(this.xhrSendHandler).handleRequest(this.request, this.response, this.wsHandler, this.session);
	}
