	@Test
	public void testNullPrimitiveWithJdkProxy() {

		class SimpleFoo implements Foo {
			@Override
			public int getValue() {
				return 100;
			}
		}

		SimpleFoo target = new SimpleFoo();
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				return null;
			}
		});

		Foo foo = (Foo) factory.getProxy();

		thrown.expect(AopInvocationException.class);
		thrown.expectMessage("Foo.getValue()");
		assertEquals(0, foo.getValue());
	}
