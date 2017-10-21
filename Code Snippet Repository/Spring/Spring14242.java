	@Test
	public void handleTransportRequestXhrAllowedOriginsNoMatch() throws Exception {
		String sockJsPath = sessionUrlPrefix + "xhr";
		setRequest("POST", sockJsPrefix + sockJsPath);
		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com", "http://mydomain2.com"));
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain3.com");
		this.service.handleRequest(this.request, this.response, sockJsPath, this.wsHandler);

		assertEquals(403, this.servletResponse.getStatus());
	}
