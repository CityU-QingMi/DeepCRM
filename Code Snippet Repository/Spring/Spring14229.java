	@Test
	public void handleInfoOptionsWithOriginAndCorsHeadersDisabled() throws Exception {
		this.servletRequest.addHeader(HttpHeaders.ORIGIN, "http://mydomain2.com");
		this.service.setAllowedOrigins(Arrays.asList("*"));
		this.service.setSuppressCors(true);

		this.servletRequest.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "Last-Modified");
		resetResponseAndHandleRequest("OPTIONS", "/echo/info", HttpStatus.NO_CONTENT);
		assertNull(this.service.getCorsConfiguration(this.servletRequest));

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		resetResponseAndHandleRequest("OPTIONS", "/echo/info", HttpStatus.FORBIDDEN);
		assertNull(this.service.getCorsConfiguration(this.servletRequest));

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com", "http://mydomain2.com", "http://mydomain3.com"));
		resetResponseAndHandleRequest("OPTIONS", "/echo/info", HttpStatus.NO_CONTENT);
		assertNull(this.service.getCorsConfiguration(this.servletRequest));
	}
