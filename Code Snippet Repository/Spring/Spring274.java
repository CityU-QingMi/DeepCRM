	@Test
	public void testCanGetSourceLocationFromJoinPoint() {
		final Object raw = new TestBean();
		ProxyFactory pf = new ProxyFactory(raw);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
				SourceLocation sloc = AbstractAspectJAdvice.currentJoinPoint().getSourceLocation();
				assertEquals("Same source location must be returned on subsequent requests", sloc, AbstractAspectJAdvice.currentJoinPoint().getSourceLocation());
				assertEquals(TestBean.class, sloc.getWithinType());
				try {
					sloc.getLine();
					fail("Can't get line number");
				}
				catch (UnsupportedOperationException ex) {
					// Expected
				}

				try {
					sloc.getFileName();
					fail("Can't get file name");
				}
				catch (UnsupportedOperationException ex) {
					// Expected
				}
			}
		});
		ITestBean itb = (ITestBean) pf.getProxy();
		// Any call will do
		itb.getAge();
	}
