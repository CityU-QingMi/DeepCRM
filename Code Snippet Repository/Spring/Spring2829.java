	private void testContext(final boolean context) throws Throwable {
		final String s = "foo";
		// Test return value
		MethodInterceptor mi = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				if (!context) {
					assertNoInvocationContext();
				}
				else {
					assertNotNull("have context", ExposeInvocationInterceptor.currentInvocation());
				}
				return s;
			}
		};
		AdvisedSupport pc = new AdvisedSupport(ITestBean.class);
		if (context) {
			pc.addAdvice(ExposeInvocationInterceptor.INSTANCE);
		}
		pc.addAdvice(mi);
		// Keep CGLIB happy
		if (requiresTarget()) {
			pc.setTarget(new TestBean());
		}
		AopProxy aop = createAopProxy(pc);

		assertNoInvocationContext();
		ITestBean tb = (ITestBean) aop.getProxy();
		assertNoInvocationContext();
		assertSame("correct return value", s, tb.getName());
	}
