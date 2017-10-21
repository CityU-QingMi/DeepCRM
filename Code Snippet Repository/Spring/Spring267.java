	@Test
	public void testMatchWithArgs() throws Exception {
		String expression = "execution(void org.springframework.tests.sample.beans.TestBean.setSomeNumber(Number)) && args(Double)";

		Pointcut pointcut = getPointcut(expression);
		ClassFilter classFilter = pointcut.getClassFilter();
		MethodMatcher methodMatcher = pointcut.getMethodMatcher();

		assertMatchesTestBeanClass(classFilter);

		// not currently testable in a reliable fashion
		//assertDoesNotMatchStringClass(classFilter);

		assertTrue("Should match with setSomeNumber with Double input",
				methodMatcher.matches(setSomeNumber, TestBean.class, new Double(12)));
		assertFalse("Should not match setSomeNumber with Integer input",
				methodMatcher.matches(setSomeNumber, TestBean.class, new Integer(11)));
		assertFalse("Should not match getAge", methodMatcher.matches(getAge, TestBean.class));
		assertTrue("Should be a runtime match", methodMatcher.isRuntime());
	}
