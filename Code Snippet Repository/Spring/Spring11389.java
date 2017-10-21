	@Test
	public void mapPathToLocation() throws Exception {
		MockServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("").build());
		exchange.getAttributes().put(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE,
				PathContainer.parsePath("/testStylesheet.css"));

		ResourceWebHandler handler = getHandler("/resources/**");
		handler.handle(exchange).block(Duration.ofSeconds(5));

		StepVerifier.create(exchange.getResponse().getBody())
				.consumeNextWith(buf -> assertEquals("test stylesheet content",
						DataBufferTestUtils.dumpString(buf, StandardCharsets.UTF_8)))
				.expectComplete()
				.verify();
	}
