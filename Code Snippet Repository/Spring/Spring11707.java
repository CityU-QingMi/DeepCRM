	@Test
	public void resolveCookieStringArgument() {
		HttpCookie cookie = new HttpCookie("name", "foo");
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").cookie(cookie).build());

		Mono<Object> mono = this.resolver.resolveArgument(
				this.cookieStringParameter, this.bindingContext, exchange);

		assertEquals("Invalid result", cookie.getValue(), mono.block());
	}
