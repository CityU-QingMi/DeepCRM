	@Test
	public void validPreFlightRequest() throws ServletException, IOException {

		MockServerHttpRequest request = MockServerHttpRequest
				.options("http://domain1.com/test.html")
				.header(HOST, "domain1.com")
				.header(ORIGIN, "http://domain2.com")
				.header(ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET.name())
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "header1, header2")
				.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		WebFilterChain filterChain = (filterExchange) -> Mono.error(
				new AssertionError("Preflight requests must not be forwarded to the filter chain"));
		filter.filter(exchange, filterChain);

		HttpHeaders headers = exchange.getResponse().getHeaders();
		assertEquals("http://domain2.com", headers.getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
		assertEquals("header1, header2", headers.getFirst(ACCESS_CONTROL_ALLOW_HEADERS));
		assertEquals("header3, header4", headers.getFirst(ACCESS_CONTROL_EXPOSE_HEADERS));
		assertEquals(123L, Long.parseLong(headers.getFirst(ACCESS_CONTROL_MAX_AGE)));
	}
