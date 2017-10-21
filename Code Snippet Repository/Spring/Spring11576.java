	@Test
	public void noTransformIfExtensionNoMatch() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("/static/foobar.file").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		this.chain = mock(ResourceTransformerChain.class);
		Resource resource = mock(Resource.class);
		given(resource.getFilename()).willReturn("foobar.file");
		given(this.chain.transform(exchange, resource)).willReturn(Mono.just(resource));

		Resource result = this.transformer.transform(exchange, resource, this.chain).block(Duration.ofMillis(5000));
		assertEquals(resource, result);
	}
