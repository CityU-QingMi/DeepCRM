	@Test
	public void handleTransportRequestIframe() throws Exception {
		String sockJsPath = "/iframe.html";
		setRequest("GET", sockJsPrefix + sockJsPath);
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertNotEquals(404, this.servletResponse.getStatus());
		assertEquals("SAMEORIGIN", this.servletResponse.getHeader("X-Frame-Options"));

		resetRequestAndResponse();
		setRequest("GET", sockJsPrefix + sockJsPath);
		this.service.setAllowedOrigins(Collections.singletonList("http://mydomain1.com"));
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertEquals(404, this.servletResponse.getStatus());
		assertNull(this.servletResponse.getHeader("X-Frame-Options"));

		resetRequestAndResponse();
		setRequest("GET", sockJsPrefix + sockJsPath);
		this.service.setAllowedOrigins(Collections.singletonList("*"));
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);
		assertNotEquals(404, this.servletResponse.getStatus());
		assertNull(this.servletResponse.getHeader("X-Frame-Options"));
	}
