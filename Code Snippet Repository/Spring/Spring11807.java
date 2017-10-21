	@SuppressWarnings("")
	private <T> T resolveValue(MethodParameter param, String body) {
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.post("/path").body(body));
		Mono<Object> result = this.resolver.readBody(param, true, new BindingContext(), exchange);
		Object value = result.block(Duration.ofSeconds(5));

		assertNotNull(value);
		assertTrue("Unexpected return value type: " + value,
				param.getParameterType().isAssignableFrom(value.getClass()));

		//no inspection unchecked
		return (T) value;
	}
