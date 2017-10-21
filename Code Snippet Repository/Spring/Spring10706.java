	@Test
	public void validActualRequest() {

		MockServerHttpRequest request = MockServerHttpRequest
				.get("http://domain1.com/test.html")
				.header(HOST, "domain1.com")
				.header(ORIGIN, "http://domain2.com")
				.header("header2", "foo")
				.build();

		WebFilterChain filterChain = (filterExchange) -> {
			try {
				HttpHeaders headers = filterExchange.getResponse().getHeaders();
				assertEquals("http://domain2.com", headers.getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
				assertEquals("header3, header4", headers.getFirst(ACCESS_CONTROL_EXPOSE_HEADERS));
			} catch (AssertionError ex) {
				return Mono.error(ex);
			}
			return Mono.empty();

		};
		filter.filter(MockServerWebExchange.from(request), filterChain);
	}
