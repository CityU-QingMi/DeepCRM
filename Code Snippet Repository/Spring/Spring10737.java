	@Test
	public void invalidPreFlightRequest() throws ServletException, IOException {

		MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.OPTIONS.name(), "/test.html");
		request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.DELETE.name());
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "header1, header2");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = (filterRequest, filterResponse) ->
				fail("Preflight requests must not be forwarded to the filter chain");
		filter.doFilter(request, response, filterChain);

		assertNull(response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
	}
