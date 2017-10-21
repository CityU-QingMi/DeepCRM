	@Test
	public void checkHeadersNotAllowed() {
		CorsConfiguration config = new CorsConfiguration();
		assertNull(config.checkHeaders(null));
		assertNull(config.checkHeaders(Arrays.asList("header1")));
		config.setAllowedHeaders(Collections.emptyList());
		assertNull(config.checkHeaders(Arrays.asList("header1")));
		config.addAllowedHeader("header2");
		config.addAllowedHeader("header3");
		assertNull(config.checkHeaders(Arrays.asList("header1")));
	}
