	@Test
	public void monoList() throws Exception {
		String body = "[{\"bar\":\"b1\",\"foo\":\"f1\"},{\"bar\":\"b2\",\"foo\":\"f2\"}]";
		ResolvableType type = forClassWithGenerics(Mono.class, forClassWithGenerics(List.class, TestBean.class));
		MethodParameter param = this.testMethod.arg(type);
		Mono<?> mono = resolveValue(param, body);

		List<?> list = (List<?>) mono.block(Duration.ofSeconds(5));
		assertEquals(Arrays.asList(new TestBean("f1", "b1"), new TestBean("f2", "b2")), list);
	}
