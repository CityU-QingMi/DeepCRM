	@Test
	public void checkOriginAllowed() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		assertEquals("*", config.checkOrigin("http://domain.com"));
		config.setAllowCredentials(true);
		assertEquals("http://domain.com", config.checkOrigin("http://domain.com"));
		config.setAllowedOrigins(Arrays.asList("http://domain.com"));
		assertEquals("http://domain.com", config.checkOrigin("http://domain.com"));
		config.setAllowCredentials(false);
		assertEquals("http://domain.com", config.checkOrigin("http://domain.com"));
	}
