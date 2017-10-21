	@Test
	public void handleTransportRequestXhrSendWithDifferentUser() throws Exception {
		String sockJsPath = sessionUrlPrefix + "xhr";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(200, this.servletResponse.getStatus()); // session created
		verify(this.xhrHandler).handleRequest(this.request, this.response, this.wsHandler, this.session);

		this.session.setPrincipal(new TestPrincipal("little red riding hood"));
		this.servletRequest.setUserPrincipal(new TestPrincipal("wolf"));

		resetResponse();
		reset(this.xhrSendHandler);
		sockJsPath = sessionUrlPrefix + "xhr_send";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(404, this.servletResponse.getStatus());
		verifyNoMoreInteractions(this.xhrSendHandler);
	}
