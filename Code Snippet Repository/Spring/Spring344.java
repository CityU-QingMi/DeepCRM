	@Test
	public void testNullPrimitiveWithCglibProxy() {

		Bar target = new Bar();
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				return null;
			}
		});

		Bar bar = (Bar) factory.getProxy();

		thrown.expect(AopInvocationException.class);
		thrown.expectMessage("Bar.getValue()");
		assertEquals(0, bar.getValue());
	}
