	@Test
	public void ambiguousHeaderPreFlightRequest() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setMethod("OPTIONS");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "header1");
		this.request.setRequestURI("/ambiguous-header");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, true);
		assertNotNull(config);
		assertArrayEquals(new String[] {"*"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedHeaders().toArray());
		assertTrue(config.getAllowCredentials());
		assertTrue(CollectionUtils.isEmpty(config.getExposedHeaders()));
		assertNull(config.getMaxAge());
	}
