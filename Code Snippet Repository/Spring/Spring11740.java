	@SuppressWarnings("")
	private <T> T resolveValue(MethodParameter param, String body) {
		MockServerHttpRequest request = post("/path").contentType(MediaType.APPLICATION_JSON).body(body);
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Object> result = this.resolver.readBody(param, true, this.bindingContext, exchange);
		Object value = result.block(Duration.ofSeconds(5));

		assertNotNull(value);
		assertTrue("Unexpected return value type: " + value,
				param.getParameterType().isAssignableFrom(value.getClass()));

		return (T) value;
	}
