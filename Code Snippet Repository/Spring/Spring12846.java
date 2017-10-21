	@Test
	public void preFlightRequest() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setMethod("OPTIONS");
		this.request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET");
		this.request.setRequestURI("/default");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, true);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertTrue(config.getAllowCredentials());
		assertArrayEquals(new String[] {"*"}, config.getAllowedHeaders().toArray());
		assertTrue(CollectionUtils.isEmpty(config.getExposedHeaders()));
		assertEquals(new Long(1800), config.getMaxAge());
	}
