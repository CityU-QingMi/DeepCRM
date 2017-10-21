	@Test
	public void filterWithHttpPut() {

		ServerWebExchange exchange = MockServerWebExchange.from(
				MockServerHttpRequest.put("/")
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
						.body("_method=DELETE"));

		this.filter.filter(exchange, this.filterChain).block(Duration.ZERO);
		assertEquals(HttpMethod.PUT, this.filterChain.getHttpMethod());
	}
