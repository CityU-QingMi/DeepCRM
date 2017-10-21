	@Test
	@SuppressWarnings("")
	public void validateFluxTestBean() throws Exception {
		String body = "[{\"bar\":\"b1\",\"foo\":\"f1\"},{\"bar\":\"b2\"}]";
		ResolvableType type = forClassWithGenerics(Flux.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		Flux<TestBean> flux = resolveValue(param, body);

		StepVerifier.create(flux)
				.expectNext(new TestBean("f1", "b1"))
				.expectError(ServerWebInputException.class)
				.verify();
	}
