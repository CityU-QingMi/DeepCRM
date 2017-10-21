	@Test
	public void checkOriginNotAllowed() {
		CorsConfiguration config = new CorsConfiguration();
		assertNull(config.checkOrigin(null));
		assertNull(config.checkOrigin("http://domain.com"));
		config.addAllowedOrigin("*");
		assertNull(config.checkOrigin(null));
		config.setAllowedOrigins(Arrays.asList("http://domain1.com"));
		assertNull(config.checkOrigin("http://domain2.com"));
		config.setAllowedOrigins(new ArrayList<>());
		assertNull(config.checkOrigin("http://domain.com"));
	}
