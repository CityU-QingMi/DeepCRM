	@Test
	public void handleTransportRequestXhrOptions() throws Exception {
		String sockJsPath = sessionUrlPrefix + "xhr";
		setRequest("OPTIONS", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(204, this.servletResponse.getStatus());
		assertNull(this.servletResponse.getHeader("Access-Control-Allow-Origin"));
		assertNull(this.servletResponse.getHeader("Access-Control-Allow-Credentials"));
		assertNull(this.servletResponse.getHeader("Access-Control-Allow-Methods"));
	}
