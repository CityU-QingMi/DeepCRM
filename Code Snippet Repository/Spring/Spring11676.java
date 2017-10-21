	@Test
	public void unregisterMapping() throws Exception {
		String key = "foo";
		this.mapping.registerMapping(key, this.handler, this.method1);
		Mono<Object> result = this.mapping.getHandler(MockServerWebExchange.from(MockServerHttpRequest.get(key).build()));

		assertNotNull(result.block());

		this.mapping.unregisterMapping(key);
		result = this.mapping.getHandler(MockServerWebExchange.from(MockServerHttpRequest.get(key).build()));

		assertNull(result.block());
		assertThat(this.mapping.getMappingRegistry().getMappings().keySet(), Matchers.not(Matchers.contains(key)));
	}
