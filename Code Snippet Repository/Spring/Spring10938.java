	@Test
	public void defaultLocale() throws Exception {
		this.resolver.setDefaultLocale(JAPANESE);
		MockServerHttpRequest request = MockServerHttpRequest.get("/").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		assertEquals(JAPANESE, this.resolver.resolveLocaleContext(exchange).getLocale());

		request = MockServerHttpRequest.get("/").acceptLanguageAsLocales(US).build();
		exchange = MockServerWebExchange.from(request);
		assertEquals(US, this.resolver.resolveLocaleContext(exchange).getLocale());
	}
