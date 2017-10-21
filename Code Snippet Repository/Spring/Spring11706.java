	@Test
	public void resolveCookieArgument() {
		HttpCookie expected = new HttpCookie("name", "foo");
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").cookie(expected).build());

		Mono<Object> mono = this.resolver.resolveArgument(
				this.cookieParameter, this.bindingContext, exchange);

		assertEquals(expected, mono.block());
	}
