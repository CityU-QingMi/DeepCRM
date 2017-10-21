	@SuppressWarnings("")
	@Test
	public void missingContentType() throws Exception {
		MockServerHttpRequest request = post("/path").body("{\"bar\":\"BARBAR\",\"foo\":\"FOOFOO\"}");
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		ResolvableType type = forClassWithGenerics(Mono.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		Mono<Object> result = this.resolver.readBody(param, true, this.bindingContext, exchange);
		Mono<TestBean> value = (Mono<TestBean>) result.block(Duration.ofSeconds(1));

		StepVerifier.create(value).expectError(UnsupportedMediaTypeStatusException.class).verify();
	}
