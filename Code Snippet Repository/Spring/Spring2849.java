	@Test
	public void testTargetCanGetInvocationWithPrivateClass() throws Throwable {
		final ExposedInvocationTestBean expectedTarget = new ExposedInvocationTestBean() {
			@Override
			protected void assertions(MethodInvocation invocation) {
				assertEquals(this, invocation.getThis());
				assertEquals("Invocation should be on ITestBean: " + invocation.getMethod(),
						ITestBean.class, invocation.getMethod().getDeclaringClass());
			}
		};

		AdvisedSupport pc = new AdvisedSupport(ITestBean.class, IOther.class);
		pc.addAdvice(ExposeInvocationInterceptor.INSTANCE);
		TrapTargetInterceptor tii = new TrapTargetInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				// Assert that target matches BEFORE invocation returns
				assertEquals("Target is correct", expectedTarget, invocation.getThis());
				return super.invoke(invocation);
			}
		};
		pc.addAdvice(tii);
		pc.setTarget(expectedTarget);
		AopProxy aop = createAopProxy(pc);

		ITestBean tb = (ITestBean) aop.getProxy();
		tb.getName();
	}
