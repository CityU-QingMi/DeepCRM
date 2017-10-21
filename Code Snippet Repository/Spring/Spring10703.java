	@Test
	public void preflightRequestMatchedAllowedMethod() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.conf.addAllowedOrigin("*");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
		assertEquals("GET,HEAD", this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
	}
