	@Test
	public void getCorsConfigurationActual() throws Exception {
		AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
		cxt.register(TestConfig.class);
		cxt.refresh();

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/path");
		request.addHeader("Origin", "http://localhost:9000");
		CorsConfiguration corsConfig = getIntrospector(cxt).getCorsConfiguration(request);

		assertNotNull(corsConfig);
		assertEquals(Collections.singletonList("http://localhost:9000"), corsConfig.getAllowedOrigins());
		assertEquals(Collections.singletonList("POST"), corsConfig.getAllowedMethods());
	}
