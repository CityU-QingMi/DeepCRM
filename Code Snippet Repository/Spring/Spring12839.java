	@Test
	public void defaultAnnotation() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setRequestURI("/default");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"*"}, config.getAllowedOrigins().toArray());
		assertTrue(config.getAllowCredentials());
		assertArrayEquals(new String[] {"*"}, config.getAllowedHeaders().toArray());
		assertTrue(CollectionUtils.isEmpty(config.getExposedHeaders()));
		assertEquals(new Long(1800), config.getMaxAge());
	}
