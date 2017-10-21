	@Test
	public void setNullValues() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(null);
		assertNull(config.getAllowedOrigins());
		config.setAllowedHeaders(null);
		assertNull(config.getAllowedHeaders());
		config.setAllowedMethods(null);
		assertNull(config.getAllowedMethods());
		config.setExposedHeaders(null);
		assertNull(config.getExposedHeaders());
		config.setAllowCredentials(null);
		assertNull(config.getAllowCredentials());
		config.setMaxAge(null);
		assertNull(config.getMaxAge());
	}
