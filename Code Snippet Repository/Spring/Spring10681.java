	@Test
	public void combine() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://domain1.com");
		config.addAllowedHeader("header1");
		config.addExposedHeader("header3");
		config.addAllowedMethod(HttpMethod.GET.name());
		config.setMaxAge(123L);
		config.setAllowCredentials(true);
		CorsConfiguration other = new CorsConfiguration();
		other.addAllowedOrigin("http://domain2.com");
		other.addAllowedHeader("header2");
		other.addExposedHeader("header4");
		other.addAllowedMethod(HttpMethod.PUT.name());
		other.setMaxAge(456L);
		other.setAllowCredentials(false);
		config = config.combine(other);
		assertEquals(Arrays.asList("http://domain1.com", "http://domain2.com"), config.getAllowedOrigins());
		assertEquals(Arrays.asList("header1", "header2"), config.getAllowedHeaders());
		assertEquals(Arrays.asList("header3", "header4"), config.getExposedHeaders());
		assertEquals(Arrays.asList(HttpMethod.GET.name(), HttpMethod.PUT.name()), config.getAllowedMethods());
		assertEquals(new Long(456), config.getMaxAge());
		assertFalse(config.getAllowCredentials());
	}
