	@Test
	public void customized() throws Exception {
		this.handlerMapping.registerHandler(new MethodLevelController());
		this.request.setRequestURI("/customized");
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		CorsConfiguration config = getCorsConfiguration(chain, false);
		assertNotNull(config);
		assertArrayEquals(new String[] {"DELETE"}, config.getAllowedMethods().toArray());
		assertArrayEquals(new String[] {"http://site1.com", "http://site2.com"}, config.getAllowedOrigins().toArray());
		assertArrayEquals(new String[] {"header1", "header2"}, config.getAllowedHeaders().toArray());
		assertArrayEquals(new String[] {"header3", "header4"}, config.getExposedHeaders().toArray());
		assertEquals(new Long(123), config.getMaxAge());
		assertFalse(config.getAllowCredentials());
	}
