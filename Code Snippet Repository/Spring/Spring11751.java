	@Test
	@SuppressWarnings("")
	public void validateMonoTestBean() throws Exception {
		String body = "{\"bar\":\"b1\"}";
		ResolvableType type = forClassWithGenerics(Mono.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		Mono<TestBean> mono = resolveValue(param, body);

		StepVerifier.create(mono).expectNextCount(0).expectError(ServerWebInputException.class).verify();
	}
