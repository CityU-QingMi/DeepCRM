	@Test
	public void handleTransportRequestXhr() throws Exception {
		String sockJsPath = sessionUrlPrefix + "xhr";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(200, this.servletResponse.getStatus());
		verify(this.xhrHandler).handleRequest(this.request, this.response, this.wsHandler, this.session);
		verify(taskScheduler).scheduleAtFixedRate(any(Runnable.class), eq(service.getDisconnectDelay()));

		assertEquals("no-store, no-cache, must-revalidate, max-age=0", this.response.getHeaders().getCacheControl());
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertNull(this.servletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
	}
