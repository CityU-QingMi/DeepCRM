	@Test
	public void validPreFlightRequest() throws ServletException, IOException {

		MockHttpServletRequest request = new MockHttpServletRequest(HttpMethod.OPTIONS.name(), "/test.html");
		request.addHeader(HttpHeaders.ORIGIN, "http://domain2.com");
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name());
		request.addHeader(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "header1, header2");
		MockHttpServletResponse response = new MockHttpServletResponse();

		FilterChain filterChain = (filterRequest, filterResponse) ->
				fail("Preflight requests must not be forwarded to the filter chain");
		filter.doFilter(request, response, filterChain);

		assertEquals("http://domain2.com", response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("header1, header2", response.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS));
		assertEquals("header3, header4", response.getHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS));
		assertEquals(123L, Long.parseLong(response.getHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE)));
	}
