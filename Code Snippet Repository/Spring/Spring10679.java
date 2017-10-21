	@Test
	public void combineWithAsteriskWildCard() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		CorsConfiguration other = new CorsConfiguration();
		other.addAllowedOrigin("http://domain.com");
		other.addAllowedHeader("header1");
		other.addExposedHeader("header2");
		other.addAllowedMethod(HttpMethod.PUT.name());
		CorsConfiguration combinedConfig = config.combine(other);
		assertEquals(Arrays.asList("http://domain.com"), combinedConfig.getAllowedOrigins());
		assertEquals(Arrays.asList("header1"), combinedConfig.getAllowedHeaders());
		assertEquals(Arrays.asList("header2"), combinedConfig.getExposedHeaders());
		assertEquals(Arrays.asList(HttpMethod.PUT.name()), combinedConfig.getAllowedMethods());
		combinedConfig = other.combine(config);
		assertEquals(Arrays.asList("http://domain.com"), combinedConfig.getAllowedOrigins());
		assertEquals(Arrays.asList("header1"), combinedConfig.getAllowedHeaders());
		assertEquals(Arrays.asList("header2"), combinedConfig.getExposedHeaders());
		assertEquals(Arrays.asList(HttpMethod.PUT.name()), combinedConfig.getAllowedMethods());
	}
