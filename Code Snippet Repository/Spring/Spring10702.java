	@Test
	public void preflightRequestWrongAllowedMethod() throws Exception {
		this.request.setMethod(HttpMethod.OPTIONS.name());
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "DELETE");
		this.conf.addAllowedOrigin("*");

		this.processor.processRequest(this.conf, this.request, this.response);
		assertEquals(HttpServletResponse.SC_FORBIDDEN, this.response.getStatus());
	}
