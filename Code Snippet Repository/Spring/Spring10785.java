	@Test
	public void queryStringIncluded() throws Exception {
		filter.setIncludeQueryString(true);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.setQueryString("booking=42");

		FilterChain filterChain = new NoOpFilterChain();
		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.beforeRequestMessage);
		assertTrue(filter.beforeRequestMessage.contains("[uri=/hotels?booking=42]"));

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains("[uri=/hotels?booking=42]"));
	}
