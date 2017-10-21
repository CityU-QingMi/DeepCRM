	@Test
	public void getCorsConfigurationPreFlight() throws Exception {
		AnnotationConfigWebApplicationContext cxt = new AnnotationConfigWebApplicationContext();
		cxt.register(TestConfig.class);
		cxt.refresh();

		// PRE-FLIGHT

		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/path");
		request.addHeader("Origin", "http://localhost:9000");
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "POST");
		CorsConfiguration corsConfig = getIntrospector(cxt).getCorsConfiguration(request);

		assertNotNull(corsConfig);
		assertEquals(Collections.singletonList("http://localhost:9000"), corsConfig.getAllowedOrigins());
		assertEquals(Collections.singletonList("POST"), corsConfig.getAllowedMethods());
	}
