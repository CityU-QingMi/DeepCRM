	@Test
	public void checkHeadersAllowed() {
		CorsConfiguration config = new CorsConfiguration();
		assertEquals(Collections.emptyList(), config.checkHeaders(Collections.emptyList()));
		config.addAllowedHeader("header1");
		config.addAllowedHeader("header2");
		assertEquals(Arrays.asList("header1"), config.checkHeaders(Arrays.asList("header1")));
		assertEquals(Arrays.asList("header1", "header2"), config.checkHeaders(Arrays.asList("header1", "header2")));
		assertEquals(Arrays.asList("header1", "header2"), config.checkHeaders(Arrays.asList("header1", "header2", "header3")));
	}
