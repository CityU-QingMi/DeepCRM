	@Test
	public void combineWithDuplicatedElements() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://domain1.com");
		config.addAllowedOrigin("http://domain2.com");
		config.addAllowedHeader("header1");
		config.addAllowedHeader("header2");
		config.addExposedHeader("header3");
		config.addExposedHeader("header4");
		config.addAllowedMethod(HttpMethod.GET.name());
		config.addAllowedMethod(HttpMethod.PUT.name());
		CorsConfiguration other = new CorsConfiguration();
		other.addAllowedOrigin("http://domain1.com");
		other.addAllowedHeader("header1");
		other.addExposedHeader("header3");
		other.addAllowedMethod(HttpMethod.GET.name());
		CorsConfiguration combinedConfig = config.combine(other);
		assertEquals(Arrays.asList("http://domain1.com", "http://domain2.com"), combinedConfig.getAllowedOrigins());
		assertEquals(Arrays.asList("header1", "header2"), combinedConfig.getAllowedHeaders());
		assertEquals(Arrays.asList("header3", "header4"), combinedConfig.getExposedHeaders());
		assertEquals(Arrays.asList(HttpMethod.GET.name(), HttpMethod.PUT.name()), combinedConfig.getAllowedMethods());
	}
