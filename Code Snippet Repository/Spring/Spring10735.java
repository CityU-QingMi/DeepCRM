	@Test
	public void invalidActualRequest() throws ServletException, IOException {

		MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.DELETE.name(), "/test.html");
		request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		request.addHeader("header2", "foo");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = (filterRequest, filterResponse) -> {
			fail("Invalid requests must not be forwarded to the filter chain");
		};
		filter.doFilter(request, response, filterChain);
		assertNull(response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
	}
