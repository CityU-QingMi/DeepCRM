	@Test
	public void testInterceptorInclusionMethods() {
		class MyInterceptor implements MethodInterceptor {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				throw new UnsupportedOperationException();
			}
		}

		NopInterceptor di = new NopInterceptor();
		NopInterceptor diUnused = new NopInterceptor();
		ProxyFactory factory = new ProxyFactory(new TestBean());
		factory.addAdvice(0, di);
		assertThat(factory.getProxy(), instanceOf(ITestBean.class));
		assertTrue(factory.adviceIncluded(di));
		assertTrue(!factory.adviceIncluded(diUnused));
		assertTrue(factory.countAdvicesOfType(NopInterceptor.class) == 1);
		assertTrue(factory.countAdvicesOfType(MyInterceptor.class) == 0);

		factory.addAdvice(0, diUnused);
		assertTrue(factory.adviceIncluded(diUnused));
		assertTrue(factory.countAdvicesOfType(NopInterceptor.class) == 2);
	}
