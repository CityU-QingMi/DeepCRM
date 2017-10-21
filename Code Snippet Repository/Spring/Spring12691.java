	@Test
	public void actualRequestWithoutCorsConfigurationProvider() throws Exception {
		this.request.setMethod(RequestMethod.GET.name());
		this.request.setRequestURI("/foo");
		this.request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		HandlerExecutionChain chain = handlerMapping.getHandler(this.request);
		assertNotNull(chain);
		assertTrue(chain.getHandler() instanceof SimpleHandler);
	}
