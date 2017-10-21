	@Test
	public void preflightRequestWithNullConfig() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.conf.addAllowedOrigin("*");

		this.processor.processRequest(null, this.request, this.response);
		assertFalse(this.response.containsHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals(HttpServletResponse.SC_FORBIDDEN, this.response.getStatus());
	}
