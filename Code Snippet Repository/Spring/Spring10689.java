	@Test
	public void preflightRequestValidRequestAndConfig() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "Header1");
		this.conf.addAllowedOrigin("*");
		this.conf.addAllowedMethod("GET");
		this.conf.addAllowedMethod("PUT");
		this.conf.addAllowedHeader("header1");
		this.conf.addAllowedHeader("header2");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("*", this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertTrue(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
		assertEquals("GET,PUT", this.response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS));
		assertFalse(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE));
		assertEquals(HttpServletResponse.SC_OK, this.response.getStatus());
	}
