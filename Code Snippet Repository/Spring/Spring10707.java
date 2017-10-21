	@Test
	public void invalidActualRequest() throws ServletException, IOException {

		MockServerHttpRequest request = MockServerHttpRequest
				.delete("http://domain1.com/test.html")
				.header(HOST, "domain1.com")
				.header(ORIGIN, "http://domain2.com")
				.header("header2", "foo")
				.build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);

		WebFilterChain filterChain = (filterExchange) -> Mono.error(
				new AssertionError("Invalid requests must not be forwarded to the filter chain"));
		filter.filter(exchange, filterChain);

		assertNull(exchange.getResponse().getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
	}
