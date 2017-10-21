	@Test
	public void testManualProxyJavaWithStaticPointcutAndTwoClassLoaders() throws Exception {

		LogUserAdvice logAdvice = new LogUserAdvice();
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(String.format("execution(* %s.TestService.*(..))", getClass().getName()));

		// Test with default class loader first...
		testAdvice(new DefaultPointcutAdvisor(pointcut, logAdvice), logAdvice, new TestServiceImpl(), "TestServiceImpl");

		// Then try again with a different class loader on the target...
		SimpleThrowawayClassLoader loader = new SimpleThrowawayClassLoader(new TestServiceImpl().getClass().getClassLoader());
		// Make sure the interface is loaded from the  parent class loader
		loader.excludeClass(TestService.class.getName());
		loader.excludeClass(TestException.class.getName());
		TestService other = (TestService) loader.loadClass(TestServiceImpl.class.getName()).newInstance();
		testAdvice(new DefaultPointcutAdvisor(pointcut, logAdvice), logAdvice, other, "TestServiceImpl");

	}
