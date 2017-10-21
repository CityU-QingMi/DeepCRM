	@Test
	public void handleInfoOptions() throws Exception {
		this.servletRequest.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "Last-Modified");
		resetResponseAndHandleRequest("OPTIONS", "/echo/info", HttpStatus.NO_CONTENT);
		assertNull(this.service.getCorsConfiguration(this.servletRequest));

		this.service.setAllowedOrigins(Arrays.asList("http://mydomain1.com"));
		resetResponseAndHandleRequest("OPTIONS", "/echo/info", HttpStatus.NO_CONTENT);
		assertNull(this.service.getCorsConfiguration(this.servletRequest));
	}
