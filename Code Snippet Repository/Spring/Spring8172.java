	@Override
	public Statement apply(Statement base, Description description) {
		Class<?> testClass = description.getTestClass();
		if (logger.isDebugEnabled()) {
			logger.debug("Applying SpringClassRule to test class [" + testClass.getName() + "]");
		}
		validateSpringMethodRuleConfiguration(testClass);
		TestContextManager testContextManager = getTestContextManager(testClass);

		Statement statement = base;
		statement = withBeforeTestClassCallbacks(statement, testContextManager);
		statement = withAfterTestClassCallbacks(statement, testContextManager);
		statement = withProfileValueCheck(statement, testClass);
		statement = withTestContextManagerCacheEviction(statement, testClass);
		return statement;
	}
