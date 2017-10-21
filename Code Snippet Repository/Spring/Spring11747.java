	@Test
	public void rxJava2ObservableTestBean() throws Exception {
		String body = "[{\"bar\":\"b1\",\"foo\":\"f1\"},{\"bar\":\"b2\",\"foo\":\"f2\"}]";
		ResolvableType type = forClassWithGenerics(io.reactivex.Observable.class, TestBean.class);
		MethodParameter param = this.testMethod.arg(type);
		io.reactivex.Observable<?> observable = resolveValue(param, body);

		assertEquals(Arrays.asList(new TestBean("f1", "b1"), new TestBean("f2", "b2")),
				observable.toList().blockingGet());
	}
