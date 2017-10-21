	@Override
	public Statement apply(Statement base, FrameworkMethod frameworkMethod, Object testInstance) {
		Method testMethod = frameworkMethod.getMethod();
		if (logger.isDebugEnabled()) {
			logger.debug("Applying SpringMethodRule to test method [" + testMethod + "]");
		}
		Class<?> testClass = testInstance.getClass();
		validateSpringClassRuleConfiguration(testClass);
		TestContextManager testContextManager = SpringClassRule.getTestContextManager(testClass);

		Statement statement = base;
		statement = withBeforeTestMethodCallbacks(statement, testMethod, testInstance, testContextManager);
		statement = withAfterTestMethodCallbacks(statement, testMethod, testInstance, testContextManager);
		statement = withTestInstancePreparation(statement, testInstance, testContextManager);
		statement = withPotentialRepeat(statement, testMethod, testInstance);
		statement = withPotentialTimeout(statement, testMethod, testInstance);
		statement = withProfileValueCheck(statement, testMethod, testInstance);
		return statement;
	}
