	@Test
	public void instantConversion() throws Exception {
		String rfc1123val = "Thu, 21 Apr 2016 17:11:08 +0100";
		MockServerHttpRequest request = MockServerHttpRequest.get("/").header("name", rfc1123val).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);

		Mono<Object> mono = this.resolver.resolveArgument(this.paramInstant, this.bindingContext, exchange);
		Object result = mono.block();

		assertTrue(result instanceof Instant);
		assertEquals(Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(rfc1123val)), result);
	}
