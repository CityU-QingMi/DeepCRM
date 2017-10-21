	@Test @SuppressWarnings("")
	public void emptyBody() throws Exception {
		MockServerHttpRequest request = post("/path").contentType(MediaType.APPLICATION_JSON).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		ResolvableType type = forClassWithGenerics(Mono.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		Mono<TestBean> result = (Mono<TestBean>) this.resolver.readBody(
				param, true, this.bindingContext, exchange).block();

		StepVerifier.create(result).expectError(ServerWebInputException.class).verify();
	}
