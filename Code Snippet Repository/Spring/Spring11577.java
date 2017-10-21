	@Test
	public void syntaxErrorInManifest() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/static/error.appcache").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		this.chain = mock(ResourceTransformerChain.class);
		Resource resource = new ClassPathResource("test/error.appcache", getClass());
		given(this.chain.transform(exchange, resource)).willReturn(Mono.just(resource));

		Resource result = this.transformer.transform(exchange, resource, this.chain).block(Duration.ofMillis(5000));
		assertEquals(resource, result);
	}
