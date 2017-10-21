	@Test
	public void uri() throws Exception {
		final MockHttpServletRequest request = new MockHttpServletRequest("POST", "/hotels");
		MockHttpServletResponse response = new MockHttpServletResponse();

		request.setQueryString("booking=42");

		FilterChain filterChain = new NoOpFilterChain();
		filter.doFilter(request, response, filterChain);

		assertNotNull(filter.beforeRequestMessage);
		assertTrue(filter.beforeRequestMessage.contains("uri=/hotel"));
		assertFalse(filter.beforeRequestMessage.contains("booking=42"));

		assertNotNull(filter.afterRequestMessage);
		assertTrue(filter.afterRequestMessage.contains("uri=/hotel"));
		assertFalse(filter.afterRequestMessage.contains("booking=42"));
	}
