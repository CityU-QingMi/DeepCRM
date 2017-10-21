	@Test
	public void preflightRequestWithEmptyHeaders() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "");
		this.conf.addAllowedHeader("*");
		this.conf.addAllowedOrigin("http://domain2.com");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertFalse(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS));
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
	}
