	@SuppressWarnings("")
	private <T> HttpEntity<T> resolveValueWithEmptyBody(ResolvableType type) {
		ServerWebExchange exchange = MockServerWebExchange.from(post("/path").build());
		MethodParameter param = this.testMethod.arg(type);
		Mono<Object> result = this.resolver.resolveArgument(param, new BindingContext(), exchange);
		HttpEntity<String> httpEntity = (HttpEntity<String>) result.block(Duration.ofSeconds(5));

		assertEquals(exchange.getRequest().getHeaders(), httpEntity.getHeaders());
		return (HttpEntity<T>) httpEntity;
	}
