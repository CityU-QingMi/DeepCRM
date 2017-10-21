	@SuppressWarnings("")
	private <T> T resolveValueWithEmptyBody(MethodParameter param) {
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.post("/path").build());
		Mono<Object> result = this.resolver.resolveArgument(param, new BindingContext(), exchange);
		Object value = result.block(Duration.ofSeconds(5));

		if (value != null) {
			assertTrue("Unexpected parameter type: " + value,
					param.getParameterType().isAssignableFrom(value.getClass()));
		}

		//no inspection unchecked
		return (T) value;
	}
