	@Test
	public void fluxTestBean() throws Exception {
		String body = "[{\"bar\":\"b1\",\"foo\":\"f1\"},{\"bar\":\"b2\",\"foo\":\"f2\"}]";
		ResolvableType type = forClassWithGenerics(Flux.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		Flux<TestBean> flux = resolveValue(param, body);

		assertEquals(Arrays.asList(new TestBean("f1", "b1"), new TestBean("f2", "b2")),
				flux.collectList().block());
	}
