	@Test
	public void invalidPreFlightRequest() throws ServletException, IOException {

		MockServerHttpRequest request = MockServerHttpRequest
				.options("http://domain1.com/test.html")
				.header(HOST, "domain1.com")
				.header(ORIGIN, "http://domain2.com")
				.header(ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.DELETE.name())
				.header(ACCESS_CONTROL_REQUEST_HEADERS, "header1, header2")
				.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		WebFilterChain filterChain = (filterExchange) -> Mono.error(
				new AssertionError("Preflight requests must not be forwarded to the filter chain"));

		filter.filter(exchange, filterChain);

		assertNull(exchange.getResponse().getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
	}
