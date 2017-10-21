	@Test
	public void validActualRequest() throws ServletException, IOException {

		MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/test.html");
		request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		request.addHeader("header2", "foo");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = (filterRequest, filterResponse) -> {
			assertEquals("http://domain2.com", response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
			assertEquals("header3, header4", response.getHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS));
		};
		filter.doFilter(request, response, filterChain);
	}
