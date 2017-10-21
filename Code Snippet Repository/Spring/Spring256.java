	@Test
	public void testMatchExplicit() {
		String expression = "execution(int org.springframework.tests.sample.beans.TestBean.getAge())";

		Pointcut pointcut = getPointcut(expression);
		ClassFilter classFilter = pointcut.getClassFilter();
		MethodMatcher methodMatcher = pointcut.getMethodMatcher();

		assertMatchesTestBeanClass(classFilter);

		// not currently testable in a reliable fashion
		//assertDoesNotMatchStringClass(classFilter);

		assertFalse("Should not be a runtime match", methodMatcher.isRuntime());
		assertMatchesGetAge(methodMatcher);
		assertFalse("Expression should match setAge() method", methodMatcher.matches(setAge, TestBean.class));
	}
