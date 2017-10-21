	@Test
	public void classLevelComposedAnnotation() throws Exception {
		this.handlerMapping.registerHandler(new ClassLevelMappingWithComposedAnnotation());

		this.request.setRequestURI("/foo");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"GET"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"http://foo.com"}, config.getAllowedOrigins().toArray());
		assertTrue(config.getAllowCredentials());
	}
