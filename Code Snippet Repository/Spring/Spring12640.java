	@Test
	public void customizedMapping() {
		this.registry.addMapping("/foo").allowedOrigins("http://domain2.com", "http://domain2.com")
				.allowedMethods("DELETE").allowCredentials(false).allowedHeaders("header1", "header2")
				.exposedHeaders("header3", "header4").maxAge(3600);
		Map<String, CorsConfiguration> configs = this.registry.getCorsConfigurations();
		assertEquals(1, configs.size());
		CorsConfiguration config = configs.get("/foo");
		assertEquals(Arrays.asList("http://domain2.com", "http://domain2.com"), config.getAllowedOrigins());
		assertEquals(Arrays.asList("DELETE"), config.getAllowedMethods());
		assertEquals(Arrays.asList("header1", "header2"), config.getAllowedHeaders());
		assertEquals(Arrays.asList("header3", "header4"), config.getExposedHeaders());
		assertEquals(false, config.getAllowCredentials());
		assertEquals(Long.valueOf(3600), config.getMaxAge());
	}
