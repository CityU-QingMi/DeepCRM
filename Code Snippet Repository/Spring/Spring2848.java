	@Test
	public void testInterceptorIsInvokedWithNoTarget() throws Throwable {
		// Test return value
		final int age = 25;
		MethodInterceptor mi = (invocation -> age);

		AdvisedSupport pc = new AdvisedSupport(ITestBean.class);
		pc.addAdvice(mi);
		AopProxy aop = createAopProxy(pc);

		ITestBean tb = (ITestBean) aop.getProxy();
		assertEquals("correct return value", age, tb.getAge());
	}
