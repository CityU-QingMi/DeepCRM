	@Test
	public void testUndeclaredUnheckedException() throws Throwable {
		final RuntimeException unexpectedException = new RuntimeException();
		// Test return value
		MethodInterceptor mi = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				throw unexpectedException;
			}
		};
		AdvisedSupport pc = new AdvisedSupport(ITestBean.class);
		pc.addAdvice(ExposeInvocationInterceptor.INSTANCE);
		pc.addAdvice(mi);

		// We don't care about the object
		pc.setTarget(new TestBean());
		AopProxy aop = createAopProxy(pc);
		ITestBean tb = (ITestBean) aop.getProxy();

		try {
			// Note: exception param below isn't used
			tb.getAge();
			fail("Should have wrapped exception raised by interceptor");
		}
		catch (RuntimeException thrown) {
			assertEquals("exception matches", unexpectedException, thrown);
		}
	}
