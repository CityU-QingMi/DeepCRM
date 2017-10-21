	@Test
	public void checkMethodAllowed() {
		CorsConfiguration config = new CorsConfiguration();
		assertEquals(Arrays.asList(HttpMethod.GET, HttpMethod.HEAD), config.checkHttpMethod(HttpMethod.GET));
		config.addAllowedMethod("GET");
		assertEquals(Arrays.asList(HttpMethod.GET), config.checkHttpMethod(HttpMethod.GET));
		config.addAllowedMethod("POST");
		assertEquals(Arrays.asList(HttpMethod.GET, HttpMethod.POST), config.checkHttpMethod(HttpMethod.GET));
		assertEquals(Arrays.asList(HttpMethod.GET, HttpMethod.POST), config.checkHttpMethod(HttpMethod.POST));
	}
