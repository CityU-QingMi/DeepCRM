	@Test
	public void noQueryStringAvailable() throws Exception {
		filter.setIncludeQueryString(true);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = new NoOpFilterChain();
		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.beforeRequestMessage);
		assertTrue(filter.beforeRequestMessage.contains("[uri=/hotels]"));

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains("[uri=/hotels]"));
	}
