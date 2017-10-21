	@Test
	public void testCanGetStaticPartFromJoinPoint() {
		final Object raw = new TestBean();
		ProxyFactory pf = new ProxyFactory(raw);
		pf.addAdvisor(ExposeInvocationInterceptor.ADVISOR);
		pf.addAdvice(new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, @Nullable Object target) throws Throwable {
				StaticPart staticPart = AbstractAspectJAdvice.currentJoinPoint().getStaticPart();
				assertEquals("Same static part must be returned on subsequent requests", staticPart, AbstractAspectJAdvice.currentJoinPoint().getStaticPart());
				assertEquals(ProceedingJoinPoint.METHOD_EXECUTION, staticPart.getKind());
				assertSame(AbstractAspectJAdvice.currentJoinPoint().getSignature(), staticPart.getSignature());
				assertEquals(AbstractAspectJAdvice.currentJoinPoint().getSourceLocation(), staticPart.getSourceLocation());
			}
		});
		ITestBean itb = (ITestBean) pf.getProxy();
		// Any call will do
		itb.getAge();
	}
