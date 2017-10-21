	@Test
	public void testDeclaredException() throws Throwable {
		final Exception expectedException = new Exception();
		// Test return value
		MethodInterceptor mi = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				throw expectedException;
			}
		};
		AdvisedSupport pc = new AdvisedSupport(ITestBean.class);
		pc.addAdvice(ExposeInvocationInterceptor.INSTANCE);
		pc.addAdvice(mi);

		// We don't care about the object
		mockTargetSource.setTarget(new Object());
		pc.setTargetSource(mockTargetSource);
		AopProxy aop = createAopProxy(pc);

		try {
			ITestBean tb = (ITestBean) aop.getProxy();
			// Note: exception param below isn't used
			tb.exceptional(expectedException);
			fail("Should have thrown exception raised by interceptor");
		}
		catch (Exception thrown) {
			assertEquals("exception matches", expectedException, thrown);
		}
	}
