	@Test
	public void handleInvalidTransportType() throws Exception {
		String sockJsPath = sessionUrlPrefix + "invalid";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		this.servletRequest.setServerName("mydomain2.com");
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(404, this.servletResponse.getStatus());
	}
